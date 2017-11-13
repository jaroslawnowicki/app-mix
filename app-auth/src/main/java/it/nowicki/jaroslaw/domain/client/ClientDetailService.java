package it.nowicki.jaroslaw.domain.client;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


@Service("clientDetailsService")
public class ClientDetailService implements ClientDetailsService {

    private final ClientDetailRepository clientDetailRepository;

    private ClientDetailService(final ClientDetailRepository clientDetailRepository) {
        this.clientDetailRepository = clientDetailRepository;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clientDetailRepository.loadClientByClientId(clientId);
        return new ClientDetails() {
            @Override
            public String getClientId() {
                return client.getGetClientId();
            }

            @Override
            public Set<String> getResourceIds() {
                return null;
            }

            @Override
            public boolean isSecretRequired() {
                return false;
            }

            @Override
            public String getClientSecret() {
                return null;
            }

            @Override
            public boolean isScoped() {
                return false;
            }

            @Override
            public Set<String> getScope() {
                return null;
            }

            @Override
            public Set<String> getAuthorizedGrantTypes() {
                return null;
            }

            @Override
            public Set<String> getRegisteredRedirectUri() {
                return null;
            }

            @Override
            public Collection<GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Integer getAccessTokenValiditySeconds() {
                return null;
            }

            @Override
            public Integer getRefreshTokenValiditySeconds() {
                return null;
            }

            @Override
            public boolean isAutoApprove(String scope) {
                return false;
            }

            @Override
            public Map<String, Object> getAdditionalInformation() {
                return null;
            }
        };
    }
}
