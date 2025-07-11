package raicode.example.chatapp.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import raicode.example.chatapp.dto.message.MessageRequestDTO;
import raicode.example.chatapp.models.Message;
import raicode.example.chatapp.services.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageRestController {

	private final MessageService messageService;

	public MessageRestController(MessageService messageService) {
		this.messageService = messageService;
	}
	
	@PostMapping("/send")
	public ResponseEntity<Message> sendMessge(@RequestBody MessageRequestDTO request) {
		return ResponseEntity.ok(messageService.sendMessage(request));
	}
	
	@GetMapping("/conversation/{conversationId}")
	public ResponseEntity<List<Message>> getConversationMessages(@PathVariable Long conversationId) {
		return ResponseEntity.ok(messageService.getMessageByConversation(conversationId));
	}
}
