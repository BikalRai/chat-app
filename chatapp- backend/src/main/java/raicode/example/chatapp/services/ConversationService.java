package raicode.example.chatapp.services;


import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import raicode.example.chatapp.custom.customException.CustomException;
import raicode.example.chatapp.custom.customException.ResourceNotFoundException;
import raicode.example.chatapp.custom.customException.ServiceNotAvailableException;
import raicode.example.chatapp.dto.conversation.GetOrCreateRequestDTO;
import raicode.example.chatapp.models.Conversation;
import raicode.example.chatapp.models.User;
import raicode.example.chatapp.repositories.ConversationRepository;
import raicode.example.chatapp.repositories.UserRepository;

@Service
public class ConversationService {
	
	Logger logger = LoggerFactory.getLogger(ConversationService.class);

	private final UserRepository userRepo;
	private final ConversationRepository conversationRepo;

	public ConversationService(UserRepository userRepo, ConversationRepository conversationRepo) {
		this.userRepo = userRepo;
		this.conversationRepo = conversationRepo;
	}
	
	public Conversation getConversationBetweenUsers(GetOrCreateRequestDTO request) {
		logger.info("Attempting to fetch conversation ...");
		
		try {
			
			Optional<Conversation> existing = conversationRepo
					.findByParticipantsContainingAndParticipantsContaining(request.getSender(), request.getReceiver());
			
			if(existing.isPresent()) {
				logger.debug("Fetched the conversation");
				return existing.get();
			}
			
			User sender = userRepo.findById(request.getSender().getId()).orElseThrow();
			User receipent = userRepo.findById(request.getReceiver().getId()).orElseThrow();
			
			Conversation conversation = new Conversation();
			conversation.setParticipants(Set.of(sender,receipent));
			
			logger.debug("Created new conversation");
			return conversationRepo.save(conversation);
			
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
			if(!existing.isPresent()) {
				logger.info("Conversation with id: {} does not exist");
				throw new ResourceNotFoundException("Conversation does not exist");
			}
			logger.debug("Fetched the conversation with id {}", conversationId);
			conversationRepo.delete(existing.get());
			
			return "Successfully deleted conversation with id: " + conversationId;
			
		} catch (DataAccessException e) {
			logger.error("Database error while fetching conversation");
			throw new ResourceNotFoundException("An unexpected error occurred while fetching conversation from the database");
		} catch (Exception e) {
			logger.error("Conversation could not be found");
			throw new CustomException("The conversation with id: " + conversationId + "does not exist");
		}
		
	}

}
