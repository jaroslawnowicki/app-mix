package it.nowicki.jaroslaw.infrastructure.mongo.client;

import org.springframework.data.repository.CrudRepository;


interface ClientDAO extends CrudRepository<ClientDTO, Long> {

    ClientDTO findByClientId(String clientId);
}
