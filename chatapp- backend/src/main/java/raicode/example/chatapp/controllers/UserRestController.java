package raicode.example.chatapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import raicode.example.chatapp.dto.user.UserProfileResponse;
import raicode.example.chatapp.dto.user.UserRequestDTO;
import raicode.example.chatapp.services.UserService;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	private final UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<UserProfileResponse>> getAllUserProfiles(){
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserProfileResponse> getUserById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.getUserById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<UserProfileResponse>> searchUsers(@RequestParam("name") String name) {
		return ResponseEntity.ok(userService.searchUserByName(name));
	}
	
	@PutMapping("/{id}/update")
	public ResponseEntity<UserProfileResponse> updateUser(@PathVariable("id") Long id, @RequestBody UserRequestDTO userRquestDto){
		return ResponseEntity.ok(userService.updateUserProfile(id, userRquestDto));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
		return ResponseEntity.ok(userService.deleteUserById(id));
	}
}
