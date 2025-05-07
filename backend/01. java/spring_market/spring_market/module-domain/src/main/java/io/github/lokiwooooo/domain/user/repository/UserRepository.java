package io.github.lokiwooooo.domain.user.repository;

import io.github.lokiwooooo.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByName(final String userName);
}

