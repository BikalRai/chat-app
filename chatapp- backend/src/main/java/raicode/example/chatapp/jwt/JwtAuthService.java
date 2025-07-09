package raicode.example.chatapp.jwt;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import raicode.example.chatapp.custom.CustomUserDetailsService;
import raicode.example.chatapp.dto.auth.AuthResponse;
import raicode.example.chatapp.dto.auth.LoginRequest;
import raicode.example.chatapp.dto.auth.RegisterRequest;
import raicode.example.chatapp.enums.Role;
import raicode.example.chatapp.enums.UserStatus;
import raicode.example.chatapp.models.Profile;
import raicode.example.chatapp.models.User;
import raicode.example.chatapp.repositories.UserRepository;

@Service
public class JwtAuthService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtAuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
    }
    
    public AuthResponse register(RegisterRequest request) {
    	User user = new User();
    	user.setEmail(request.getEmail());
    	user.setPassword(passwordEncoder.encode(request.getPassword()));
    	user.setRole(Role.USER);
    	user.setCreatedAt(LocalDateTime.now());
    	user.setLastActive(LocalDateTime.now());
    	user.setUserStatus(UserStatus.OFFLINE);
    	
    	Profile profile = new Profile();
    	user.setProfile(profile);
    	
    	userRepository.save(user);
    	
    	UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
    	
    	String token = jwtUtils.generateToken(userDetails);
    	
    	return new AuthResponse(token);
    }
    
    // method for both authentication and login
    public AuthResponse authenticate(LoginRequest request) {
    	authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
    	
    	UserDetails userDetails = customUserDetailsService.loadUserByUsername(request.getEmail());
    	
    	String token = jwtUtils.generateToken(userDetails);
    	
    	return new AuthResponse(token);
    }

}
