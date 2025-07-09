package raicode.example.chatapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import raicode.example.chatapp.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

}
