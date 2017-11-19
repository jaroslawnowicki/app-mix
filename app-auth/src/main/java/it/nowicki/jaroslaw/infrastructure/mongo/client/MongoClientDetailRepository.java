package it.nowicki.jaroslaw.infrastructure.mongo.client;

import it.nowicki.jaroslaw.domain.client.*;
import it.nowicki.jaroslaw.domain.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by jarek on 13.11.17.
 */
@Repository
public class MongoClientDetailRepository implements ClientDetailRepository {

    private final ClientDAO clientDAO;
    private final ClientDAOMapper clientDAOMapper;

    @Autowired
    public MongoClientDetailRepository(final ClientDAO clientDAO,
                                       final ClientDAOMapper clientDAOMapper) {
        this.clientDAO = clientDAO;
        this.clientDAOMapper = clientDAOMapper;
    }

    @Override
    public Client loadClientByClientId(String clientId) {
        return clientDAOMapper.map(clientDAO.findByClientId(clientId));
    }
}
