package com.example.userservice.jpa;


import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    UserEntity findByUserId(final String userId) throws Exception;

    UserEntity findByEmail(String username);
}
