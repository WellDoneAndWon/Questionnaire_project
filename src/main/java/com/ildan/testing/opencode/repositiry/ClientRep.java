package com.ildan.testing.opencode.repositiry;

import com.ildan.testing.opencode.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRep extends JpaRepository<User, Long> {
    User findUserByUsername(String userName);

    Optional<User> findByUsername(String name);
}