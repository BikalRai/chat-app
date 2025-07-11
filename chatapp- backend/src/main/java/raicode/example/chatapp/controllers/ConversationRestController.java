package raicode.example.chatapp.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import raicode.example.chatapp.dto.conversation.GetOrCreateRequestDTO;
import raicode.example.chatapp.models.Conversation;
import raicode.example.chatapp.services.ConversationService;

@RestController
@RequestMapping("/api/conversations")
public class ConversationRestController {
	
	private final ConversationService conversationService;

	public ConversationRestController(ConversationService conversationService) {
		this.conversationService = conversationService;
	}
	
	@PostMapping("/get-or-create")
	public ResponseEntity<Conversation> getOrCreateConversation(@RequestBody GetOrCreateRequestDTO request) {
		return ResponseEntity.ok(conversationService.getConversationBetweenUsers(request));
	}

}
