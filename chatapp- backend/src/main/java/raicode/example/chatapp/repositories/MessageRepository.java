package raicode.example.chatapp.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import raicode.example.chatapp.models.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
	
	List<Message> findByConversationIdOrderByTimeStampAsc(Long conversationId);

}
