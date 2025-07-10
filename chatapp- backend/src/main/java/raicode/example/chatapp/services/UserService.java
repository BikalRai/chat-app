package raicode.example.chatapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import raicode.example.chatapp.custom.customException.CustomException;
import raicode.example.chatapp.custom.customException.ResourceNotFoundException;
import raicode.example.chatapp.custom.customException.ServiceNotAvailableException;
import raicode.example.chatapp.dto.user.UserProfileResponse;
import raicode.example.chatapp.dto.user.UserRequestDTO;
import raicode.example.chatapp.models.Profile;
import raicode.example.chatapp.models.User;
import raicode.example.chatapp.repositories.UserRepository;

@Service
public class UserService {

	private static final Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private UserRepository userRepo;

	public List<UserProfileResponse> getAllUsers() throws CustomException {

		logger.info("Retrieveing all user profiles....");

		try {
			List<User> allUsers = userRepo.findAll();

			if (allUsers.isEmpty()) {
				logger.info("No users found in the database");
				return new ArrayList<>();
			}

			logger.debug("Successfully retrieved {} user profiles from the database", allUsers.size());
			return allUsers.stream().map(user -> new UserProfileResponse(user, user.getProfile()))
					.collect(Collectors.toList());

		} catch (DataAccessException e) {
			logger.error("Database error while while fetching all users: {}", e.getMessage());
			throw new ServiceNotAvailableException("Database error while while fetching all users:");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while fetching all users: {}", e.getMessage());
			throw new CustomException("An unexpected error occurred while fetching all users");
		}
	}

	public UserProfileResponse getUserById(Long id) {
		logger.info("Attempting to fetch user based on id: {}", id);

		try {
			Optional<User> existingUser = userRepo.findById(id);

			if (!existingUser.isPresent()) {
				logger.info("User with id: {} not found", id);
				throw new ResourceNotFoundException("User with id: " + id + " not found.");
			}

			logger.debug("Successfully fetched user with id: {}", id);
			User user = existingUser.get();

			return new UserProfileResponse(user, user.getProfile());

		} catch (DataAccessException e) {
			logger.error("An unexpected database error occurred while fetching user data: {}", e.getMessage());
			throw new ServiceNotAvailableException("An unexpected database error occurred while fetching user data");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while fetching user data: {}", e.getMessage());
			throw new CustomException("An unexpected error occurred while fetching user data with id: " + id);
		}
	}

	public List<UserProfileResponse> searchUserByName(String name) {
		logger.info("Attempting to search users");

		try {
			List<User> allUsersWithSearchName = userRepo.findByProfileUsernameContainingIgnoreCase(name);

			if (allUsersWithSearchName.isEmpty()) {
				logger.info("No users with name: {}", name);
				return new ArrayList<>();
			}

			logger.debug("Found users with the username: {}", name);
			return allUsersWithSearchName.stream().map(user -> new UserProfileResponse(user, user.getProfile()))
					.collect(Collectors.toList());
		} catch (DataAccessException e) {
			logger.error("An unexpected database error occurred while fetching users: {}", e.getMessage());
			throw new ServiceNotAvailableException("An unexpected database error occurred while fetching users");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while fetching users with username: {}, ", name, e.getMessage());
			throw new CustomException("User with the username: " + name + " not found");
		}
	}

	public UserProfileResponse updateUserProfile(Long id, UserRequestDTO userReqDTO) {
		logger.info("Attempting to fetch user with id: {}", id);

		try {
			Optional<User> existingUser = userRepo.findById(id);

			if (!existingUser.isPresent()) {
				logger.info("User with id: {} not found");
				throw new ResourceNotFoundException("User with id: " + id + " not found");
			}

			logger.debug("Successfully fetched user with id: {}", id);
			User user = existingUser.get();

			user.setEmail(userReqDTO.getEmail());

			if (user.getProfile() != null && userReqDTO.getProfile() != null) {
				Profile existingProfile = user.getProfile();
				Profile requestProfile = userReqDTO.getProfile();
				profileCopy(requestProfile, existingProfile);
			}

			if (user.getProfile() == null && userReqDTO.getProfile() != null) {
				Profile newProfile = new Profile();
				profileCopy(user.getProfile(), newProfile);
				user.setProfile(newProfile);
			}

			User updatedUser = userRepo.save(user);
			logger.debug("Successfully updated user profile with id: {}", id);

			return new UserProfileResponse(updatedUser, updatedUser.getProfile());

		} catch (DataAccessException e) {
			logger.error("An unexpected database error occurred while fetching user data: {}", e.getMessage());
			throw new ServiceNotAvailableException("An unexpected database error occurred while fetching user data");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while updating user data: {}", e.getMessage());
			throw new CustomException("An unexpected error occurred while updating user data with id: " + id);
		}
	}

	public String deleteUserById(Long id) {
		logger.info("Attempting to fetch user with id: {}", id);

		try {
			Optional<User> existingUser = userRepo.findById(id);

			if (!existingUser.isPresent()) {
				logger.info("User with id: {} does not exist", id);
				throw new ResourceNotFoundException("User with id: " + id + " not found");
			}

			logger.debug("Successfully fetched user with id: {}", id);
			User user = existingUser.get();

			userRepo.delete(user);
			logger.debug("Successfully delete user with id: {}", id);

			return "Successfully delete user with id: " + id;
		} catch (DataAccessException e) {
			logger.error("An unexpected database error occurred while fetching user data: {}", e.getMessage());
			throw new ServiceNotAvailableException("An unexpected database error occurred while fetching user data");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while deleting user data: {}", e.getMessage());
			throw new CustomException("An unexpected error occurred while deleting user data with id: " + id);
		}
	}

	// helper / wrapper method
	private void profileCopy(Profile source, Profile target) {
		target.setFirstName(source.getFirstName());
		target.setLastName(source.getLastName());
		target.setPhoneNumber(source.getPhoneNumber());
		;
		target.setCountry(source.getCountry());
		;
		target.setBio(source.getBio());
		target.setUsername(source.getUsername());
	}
}
