package raicode.example.chatapp.dto.conversation;

import raicode.example.chatapp.models.User;

public class GetOrCreateRequestDTO {

	private User sender;
	private User receiver;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

}
