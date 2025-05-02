package io.github.leonardocapristo.cadastroclientesapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.leonardocapristo.cadastroclientesapi.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	

}
