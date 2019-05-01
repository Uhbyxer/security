package com.acme.springsecurity.repository;

import com.acme.springsecurity.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	User findByUsername(String username);
}
