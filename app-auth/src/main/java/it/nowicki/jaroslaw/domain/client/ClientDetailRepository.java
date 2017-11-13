package it.nowicki.jaroslaw.domain.client;

public interface ClientDetailRepository {

    Client loadClientByClientId(String clientId);
}
