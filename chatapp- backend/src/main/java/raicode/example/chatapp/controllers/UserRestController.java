package raicode.example.chatapp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import raicode.example.chatapp.dto.user.UserProfileResponse;
import raicode.example.chatapp.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
	
	private final UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<?> getAllUserProfiles() {
		try {
			return ResponseEntity.ok(userService.getAllUsers());
		} catch(Exception e) {
			Map<String, String> error = new HashMap<>();
			error.put("error", e.getMessage());
			return ResponseEntity.badRequest().body(error);
		}
	}
}
