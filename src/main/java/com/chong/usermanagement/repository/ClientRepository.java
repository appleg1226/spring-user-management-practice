package com.chong.usermanagement.repository;

import com.chong.usermanagement.domain.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String> {
}
