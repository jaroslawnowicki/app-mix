package it.nowicki.jaroslaw.domain.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service("clientService")
public class ClientService implements org.springframework.security.oauth2.provider.ClientDetailsService {

    private final ClientDetailRepository clientDetailRepository;

    @Autowired
    public ClientService(final ClientDetailRepository clientDetailRepository) {
        this.clientDetailRepository = clientDetailRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println("dziala 111");
//        Client client = clientDetailRepository.loadClientByClientId(clientId);

        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(clientId);
        details.setAuthorizedGrantTypes(Arrays.asList("password", "refresh_token", "client_credentials"));
        details.setScope(Arrays.asList("trust"));
        details.setAutoApproveScopes(Arrays.asList("trust"));
        details.setAuthorities(Arrays.asList(new SimpleGrantedAuthority("client_role2")));
        details.setResourceIds(Arrays.asList("clients"));
        details.setClientSecret("secret");

        return details;
    }
}
