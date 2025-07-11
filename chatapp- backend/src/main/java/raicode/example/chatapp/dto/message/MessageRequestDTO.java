package raicode.example.chatapp.dto.message;

public class MessageRequestDTO {

	private Long senderId;
	private Long conversationId;
	private String content;
	private String contentType;

	public MessageRequestDTO() {
	}

	public MessageRequestDTO(Long senderId, Long conversationId, String content, String contentType) {
		this.senderId = senderId;
		this.conversationId = conversationId;
		this.content = content;
		this.contentType = contentType;
	}

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getConversationId() {
		return conversationId;
	}

	public void setConversationId(Long conversationId) {
		this.conversationId = conversationId;
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

	@Override
	public String toString() {
		return "MessageRequestDTO [senderId=" + senderId + ", conversationId=" + conversationId + ", content=" + content
				+ ", contentType=" + contentType + "]";
	}

}
