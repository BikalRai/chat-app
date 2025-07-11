package raicode.example.chatapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import raicode.example.chatapp.models.Conversation;
import raicode.example.chatapp.models.User;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	
	Optional<Conversation> findByParticipantsContainingAndParticipantsContaining(User user1, User user2);
}
