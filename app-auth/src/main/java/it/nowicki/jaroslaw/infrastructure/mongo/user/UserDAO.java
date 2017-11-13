package it.nowicki.jaroslaw.infrastructure.mongo.user;

import org.springframework.data.repository.CrudRepository;

interface UserDAO extends CrudRepository<UserDTO, Long> {

    UserDTO findOneByUsername(String username);
}

