package raicode.example.chatapp.services;

import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import raicode.example.chatapp.custom.customException.CustomException;
import raicode.example.chatapp.custom.customException.ResourceNotFoundException;
import raicode.example.chatapp.custom.customException.ServiceNotAvailableException;
import raicode.example.chatapp.dto.conversation.ConversationResponseDTO;
import raicode.example.chatapp.dto.conversation.GetOrCreateRequestDTO;
import raicode.example.chatapp.models.Conversation;
import raicode.example.chatapp.models.Message;
import raicode.example.chatapp.models.User;
import raicode.example.chatapp.repositories.ConversationRepository;
import raicode.example.chatapp.repositories.MessageRepository;
import raicode.example.chatapp.repositories.UserRepository;

@Service
public class ConversationService {

	Logger logger = LoggerFactory.getLogger(ConversationService.class);

	private final UserRepository userRepo;
	private final ConversationRepository conversationRepo;
	private final MessageRepository messageRepo;

	public ConversationService(UserRepository userRepo, ConversationRepository conversationRepo,
			MessageRepository messageRepo) {
		this.userRepo = userRepo;
		this.conversationRepo = conversationRepo;
		this.messageRepo = messageRepo;
	}

	public ConversationResponseDTO getConversationBetweenUsers(GetOrCreateRequestDTO request) {
		logger.info("Attempting to fetch conversation ...");

		try {

			Optional<Conversation> existing = conversationRepo.findByUsers(request.getSender(), request.getReceiver());

			if (existing.isPresent()) {
				logger.debug("Fetched the conversation");
				return new ConversationResponseDTO(existing.get());
			}

			User sender = userRepo.findById(request.getSender().getId()).orElseThrow();
			User receipent = userRepo.findById(request.getReceiver().getId()).orElseThrow();

			Conversation conversation = new Conversation();
			conversation.setParticipants(Set.of(sender, receipent));

			sender.getConversations().add(conversation);
			receipent.getConversations().add(conversation);

			logger.debug("Created new conversation");
			return new ConversationResponseDTO(conversationRepo.save(conversation));

		} catch (DataAccessException e) {
			logger.error("Database error while while attempting to send message: {}", e.getMessage());
			throw new ServiceNotAvailableException("Database error while while attempting to send message:");
		} catch (Exception e) {
			logger.error("An unexpected error occurred while fetching data: {}", e.getMessage());
			throw new CustomException("An unexpected error occurred while data: either user or converstion");
		}
	}

	public String deleteConversation(Long conversationId) {
		logger.info("Attempting to delete conversation ...");

		try {
			Optional<Conversation> existing = conversationRepo.findById(conversationId);
			if (!existing.isPresent()) {
				logger.info("Conversation with id: {} does not exist");
				throw new ResourceNotFoundException("Conversation does not exist");
			}
			logger.debug("Fetched the conversation with id {}", conversationId);
			conversationRepo.delete(existing.get());

			return "Successfully deleted conversation with id: " + conversationId;

		} catch (DataAccessException e) {
			logger.error("Database error while fetching conversation");
			throw new ResourceNotFoundException(
					"An unexpected error occurred while fetching conversation from the database");
		} catch (Exception e) {
			logger.error("Conversation could not be found");
			throw new CustomException("The conversation with id: " + conversationId + "does not exist");
		}

	}

	public String deleteMessageFromConversation(Long conversationId, Long messageId) {
		logger.info("Attempting to delete message with id: {} from conversation with id: {}", messageId,
				conversationId);

		try {

			Conversation conversation = conversationRepo.findById(conversationId).orElseThrow(() -> {
				logger.debug("Conversatio with id: {} not found", conversationId);
				return new ResourceNotFoundException("Conversation not found");
			});

			Message message = messageRepo.findById(messageId).orElseThrow(() -> {
				logger.debug("Message with id: {} not found", messageId);
				return new ResourceNotFoundException("Message not found");
			});

			if (!message.getConversation().getId().equals(conversation.getId())) {
				logger.debug("Message with id: {} does not belong to the conversation with id: {}", messageId,
						conversationId);
				throw new CustomException("Message does not belong to the conversation");
			}

			messageRepo.delete(message);
			logger.info("Successfully deleted message with id: {} from the conversation", messageId);

			return "Successfully deleted message with id: " + messageId + " from the conversation with id: "
					+ conversationId;

		} catch (DataAccessException e) {
			logger.error("Database error while attempting to delete message: {}", e.getMessage());
			throw new ServiceNotAvailableException("Database error while deleting message");
		} catch (Exception e) {
			logger.error("Unexpected error while deleting message: {}", e.getMessage());
			throw new CustomException("Unexpected error occurred while deleting message");
		}
	}

}
