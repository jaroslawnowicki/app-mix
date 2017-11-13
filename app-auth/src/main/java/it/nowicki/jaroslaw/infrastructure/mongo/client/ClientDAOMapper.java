package it.nowicki.jaroslaw.infrastructure.mongo.client;

import it.nowicki.jaroslaw.domain.client.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientDAOMapper {

    public ClientDTO map(Client client) {
        return new ClientDTO();
    }

    public Client map(ClientDTO clientDTO) {
        return new Client();
    }
}
