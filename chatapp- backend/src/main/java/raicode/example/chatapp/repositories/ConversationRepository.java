package raicode.example.chatapp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import raicode.example.chatapp.models.Conversation;
import raicode.example.chatapp.models.User;

@Repository
public interface ConversationRepository extends JpaRepository<Conversation, Long> {
	
	@Query("SELECT c FROM Conversation c WHERE :user1 MEMBER OF c.participants "
			+ "AND :user2 MEMBER OF c.participants AND SIZE(c.participants) = 2")
	Optional<Conversation> findByUsers(@Param("user1") User user1, @Param("user2") User user2);
	
}
