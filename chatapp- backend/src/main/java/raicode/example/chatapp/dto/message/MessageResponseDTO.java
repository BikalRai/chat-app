package raicode.example.chatapp.dto.message;

import raicode.example.chatapp.dto.user.UserProfileResponse;
import raicode.example.chatapp.enums.MessageStatus;
import raicode.example.chatapp.models.Message;

public class MessageResponseDTO {

	private Long id;
	private String content;
	private String contentType;
	private MessageStatus messageStatus;
	private UserProfileResponse sender;

	public MessageResponseDTO() {
	}

	public MessageResponseDTO(Message message) {
		this.id = message.getId();
		this.content = message.getContent();
		this.contentType = message.getContent();
		this.messageStatus = message.getMessageStatus();
		this.sender = new UserProfileResponse(message.getSender(), message.getSender().getProfile());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public MessageStatus getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

	public UserProfileResponse getSender() {
		return sender;
	}

	public void setSender(UserProfileResponse sender) {
		this.sender = sender;
	}

}
