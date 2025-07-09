package raicode.example.chatapp.dto.user;

import java.time.LocalDateTime;

import raicode.example.chatapp.models.Profile;
import raicode.example.chatapp.models.User;

public class UserProfileResponse {

	private Long id;
	private String email;
	private String username;
	private String firstName;
	private String lastName;
	private String bio;
	private String country;
	private String phoneNumber;
	private LocalDateTime createdAt;
	private LocalDateTime lastActive;

	public UserProfileResponse() {
	}

	public UserProfileResponse(User user, Profile profile) {
		this.id = user.getId();
		this.email = user.getEmail();
		this.username = profile.getUsername();
		this.firstName = profile.getFirstName();
		this.lastName = profile.getLastName();
		this.bio = profile.getBio();
		this.country = profile.getCountry();
		this.phoneNumber = profile.getPhoneNumber();
		this.createdAt = user.getCreatedAt();
		this.lastActive = user.getLastActive();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getLastActive() {
		return lastActive;
	}

	public void setLastActive(LocalDateTime lastActive) {
		this.lastActive = lastActive;
	}

	@Override
	public String toString() {
		return "UserProfileResponse [id=" + id + ", email=" + email + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", bio=" + bio + ", country=" + country + ", phoneNumber="
				+ phoneNumber + ", createdAt=" + createdAt + ", lastActive=" + lastActive + "]";
	}

}
