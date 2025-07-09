package raicode.example.chatapp.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import raicode.example.chatapp.dto.user.UserProfileResponse;
import raicode.example.chatapp.models.User;
import raicode.example.chatapp.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	public List<UserProfileResponse> getAllUsers() {
		
		List<User> allUsers = userRepo.findAll();
		
		return allUsers.stream()
				.map(user -> new UserProfileResponse(user, user.getProfile()))
				.collect(Collectors.toList());
	}

}
