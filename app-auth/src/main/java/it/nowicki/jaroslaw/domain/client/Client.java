package it.nowicki.jaroslaw.domain.client;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Map;
import java.util.Set;


public class Client {

    private String getClientId;

    private Set<String> getResourceIds;

    private boolean isSecretRequired;

    private String getClientSecret;

    private boolean isScoped;

    private Set<String> getScope;

    private Set<String> getAuthorizedGrantTypes;

    private Set<String> getRegisteredRedirectUri;

    private Collection<GrantedAuthority> getAuthorities;

    private Integer getAccessTokenValiditySeconds;

    private Integer getRefreshTokenValiditySeconds;

    private boolean isAutoApprove;

    private Map<String, Object> getAdditionalInformation;

    public String getGetClientId() {
        return getClientId;
    }

    public void setGetClientId(String getClientId) {
        this.getClientId = getClientId;
    }

    public Set<String> getGetResourceIds() {
        return getResourceIds;
    }

    public void setGetResourceIds(Set<String> getResourceIds) {
        this.getResourceIds = getResourceIds;
    }

    public boolean isSecretRequired() {
        return isSecretRequired;
    }

    public void setSecretRequired(boolean secretRequired) {
        isSecretRequired = secretRequired;
    }

    public String getGetClientSecret() {
        return getClientSecret;
    }

    public void setGetClientSecret(String getClientSecret) {
        this.getClientSecret = getClientSecret;
    }

    public boolean isScoped() {
        return isScoped;
    }

    public void setScoped(boolean scoped) {
        isScoped = scoped;
    }

    public Set<String> getGetScope() {
        return getScope;
    }

    public void setGetScope(Set<String> getScope) {
        this.getScope = getScope;
    }

    public Set<String> getGetAuthorizedGrantTypes() {
        return getAuthorizedGrantTypes;
    }

    public void setGetAuthorizedGrantTypes(Set<String> getAuthorizedGrantTypes) {
        this.getAuthorizedGrantTypes = getAuthorizedGrantTypes;
    }

    public Set<String> getGetRegisteredRedirectUri() {
        return getRegisteredRedirectUri;
    }

    public void setGetRegisteredRedirectUri(Set<String> getRegisteredRedirectUri) {
        this.getRegisteredRedirectUri = getRegisteredRedirectUri;
    }

    public Collection<GrantedAuthority> getGetAuthorities() {
        return getAuthorities;
    }

    public void setGetAuthorities(Collection<GrantedAuthority> getAuthorities) {
        this.getAuthorities = getAuthorities;
    }

    public Integer getGetAccessTokenValiditySeconds() {
        return getAccessTokenValiditySeconds;
    }

    public void setGetAccessTokenValiditySeconds(Integer getAccessTokenValiditySeconds) {
        this.getAccessTokenValiditySeconds = getAccessTokenValiditySeconds;
    }

    public Integer getGetRefreshTokenValiditySeconds() {
        return getRefreshTokenValiditySeconds;
    }

    public void setGetRefreshTokenValiditySeconds(Integer getRefreshTokenValiditySeconds) {
        this.getRefreshTokenValiditySeconds = getRefreshTokenValiditySeconds;
    }

    public boolean isAutoApprove() {
        return isAutoApprove;
    }

    public void setAutoApprove(boolean autoApprove) {
        isAutoApprove = autoApprove;
    }

    public Map<String, Object> getGetAdditionalInformation() {
        return getAdditionalInformation;
    }

    public void setGetAdditionalInformation(Map<String, Object> getAdditionalInformation) {
        this.getAdditionalInformation = getAdditionalInformation;
    }
}
