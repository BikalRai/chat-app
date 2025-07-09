package raicode.example.chatapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import raicode.example.chatapp.dto.auth.AuthResponse;
import raicode.example.chatapp.dto.auth.LoginRequest;
import raicode.example.chatapp.dto.auth.RegisterRequest;
import raicode.example.chatapp.jwt.JwtAuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final JwtAuthService jwtAuthService;
	
	public AuthController (JwtAuthService jwtAuthService) {
		this.jwtAuthService = jwtAuthService;
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> logn(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(jwtAuthService.authenticate(request));
	}
	
	@PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(jwtAuthService.register(request));
    }
}
