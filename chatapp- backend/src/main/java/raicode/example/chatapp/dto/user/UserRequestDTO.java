package raicode.example.chatapp.dto.user;

import raicode.example.chatapp.models.Profile;
import raicode.example.chatapp.models.User;

public class UserRequestDTO {

	private String email;
	private Profile profile;

	public UserRequestDTO() {
	}

	public UserRequestDTO(User user, Profile profile) {
		this.email = user.getEmail();
		this.profile = profile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

}
