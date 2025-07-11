package raicode.example.chatapp.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import raicode.example.chatapp.custom.customException.CustomException;
import raicode.example.chatapp.custom.customException.ResourceNotFoundException;
import raicode.example.chatapp.custom.customException.ServiceNotAvailableException;
import raicode.example.chatapp.dto.message.MessageRequestDTO;
import raicode.example.chatapp.enums.MessageStatus;
import raicode.example.chatapp.enums.MessageType;
import raicode.example.chatapp.models.Conversation;
import raicode.example.chatapp.models.Message;
import raicode.example.chatapp.models.User;
import raicode.example.chatapp.repositories.ConversationRepository;
import raicode.example.chatapp.repositories.MessageRepository;
import raicode.example.chatapp.repositories.UserRepository;

@Service
public class MessageService {
	
	Logger logger = LoggerFactory.getLogger(MessageService.class);

	private final MessageRepository messageRepo;
	private final ConversationRepository conversationRepo;
	private final UserRepository userRepo;

	public MessageService(MessageRepository messageRepo, ConversationRepository conversationRepo,
			UserRepository userRepo) {
		this.messageRepo = messageRepo;
		this.conversationRepo = conversationRepo;
		this.userRepo = userRepo;
	}
	
	public Message sendMessage(MessageRequestDTO request) {
		
		logger.info("Attempting to send message....");
		
		try {
			
			User sender = userRepo.findById(request.getSenderId())
					.orElseThrow(() -> new ResourceNotFoundException("Sender not found"));
			
			Conversation conversation = conversationRepo.findById(request.getConversationId())
					.orElseThrow(() -> new ResourceNotFoundException("Conversation not found"));
			
			logger.debug("Creating and sending message");
			Message message = new Message();
			message.setSender(sender);
			message.setContent(request.getContent());
			message.setContentType(MessageType.valueOf(request.getContentType()));
			message.setConversation(conversation);
			message.setMessageStatus(MessageStatus.SENT);
			message.setTimeStamp(LocalDateTime.now());
			
			return messageRepo.save(message);
			
		} catch (DataAccessException e) {
			logger.error("Database error while while attempting to send message: {}", e.getMessage());
			throw new ServiceNotAvailableException("Database error while while attempting to send message:");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while fetching data: {}", e.getMessage());
			throw new CustomException("An unexpected error occurred while data: either user or converstion");
		}
		
	}
	
	public List<Message> getMessageByConversation(Long conversationId) {
		return messageRepo.findByConversationIdOrderByTimeStampAsc(conversationId);
	}

}
