package com.chong.usermanagement.repository;

import com.chong.usermanagement.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}
