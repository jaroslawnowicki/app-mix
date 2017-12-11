package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.security

import org.keycloak.KeycloakSecurityContext
import org.keycloak.representations.AccessToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.web.context.annotation.RequestScope

/**
 * Created by jarek on 16.06.17.
 */
@Component
@RequestScope
class UserInfo @Autowired constructor(val securityContext: KeycloakSecurityContext, val accessToken: AccessToken) {

    fun auth(): Authentication {
        return SecurityContextHolder.getContext().getAuthentication()
    }

    fun principal(): User {
        return SecurityContextHolder.getContext().authentication.principal as User
    }

    fun name(): String = accessToken.preferredUsername

    fun email(): String = accessToken.email


//    println(securityContext.tokenString)
//    println(accessToken.preferredUsername)
//    println(accessToken.givenName)
//    println(accessToken.email)
//    println(accessToken.id)
//    println(accessToken.nickName)
//    println(accessToken.familyName)
//    println(accessToken.middleName)
}