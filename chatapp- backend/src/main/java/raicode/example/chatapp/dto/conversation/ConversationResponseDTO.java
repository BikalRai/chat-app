package raicode.example.chatapp.dto.conversation;

import java.util.List;
import java.util.stream.Collectors;

import raicode.example.chatapp.dto.message.MessageResponseDTO;
import raicode.example.chatapp.dto.user.UserProfileResponse;
import raicode.example.chatapp.models.Conversation;

public class ConversationResponseDTO {
	private Long id;
	private String name;
	private List<UserProfileResponse> participants;
	private List<MessageResponseDTO> messages;

	public ConversationResponseDTO() {
	}

	public ConversationResponseDTO(Conversation conversation) {
		this.id = conversation.getId();
		this.name = conversation.getName();
		this.participants = conversation.getParticipants().stream()
				.map(user -> new UserProfileResponse(user, user.getProfile()))
				.collect(Collectors.toList());
		this.messages = conversation.getMessage().stream()
				.map(message -> new MessageResponseDTO(message))
				.collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserProfileResponse> getParticipants() {
		return participants;
	}

	public void setParticipants(List<UserProfileResponse> participants) {
		this.participants = participants;
	}

	public List<MessageResponseDTO> getMessages() {
		return messages;
	}

	public void setMessages(List<MessageResponseDTO> messages) {
		this.messages = messages;
	}

}
