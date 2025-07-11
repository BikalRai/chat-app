package raicode.example.chatapp.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import raicode.example.chatapp.enums.MessageStatus;
import raicode.example.chatapp.enums.MessageType;

@Entity
@Table(name = "messages")
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;

	@Enumerated(EnumType.STRING)
	private MessageType contentType;

	@Enumerated(EnumType.STRING)
	private MessageStatus messageStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sender_id", nullable = false)
	private User sender;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "conversation_id")
	private Conversation conversation;

	private LocalDateTime timeStamp;

	public Message() {

	}

	public Message(Long id, String content, MessageType contentType, MessageStatus messageStatus, User sender,
			Conversation conversation, LocalDateTime timeStamp) {
		this.id = id;
		this.content = content;
		this.contentType = contentType;
		this.messageStatus = messageStatus;
		this.sender = sender;
		this.conversation = conversation;
		this.timeStamp = timeStamp;
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

	public MessageType getContentType() {
		return contentType;
	}

	public void setContentType(MessageType contentType) {
		this.contentType = contentType;
	}

	public MessageStatus getMessageStatus() {
		return messageStatus;
	}

	public void setMessageStatus(MessageStatus messageStatus) {
		this.messageStatus = messageStatus;
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public Conversation getConversation() {
		return conversation;
	}

	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + ", contentType=" + contentType + ", messageStatus="
				+ messageStatus + ", sender=" + sender + ", conversation=" + conversation + ", timeStamp=" + timeStamp
				+ "]";
	}

}
