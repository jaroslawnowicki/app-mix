package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component

/**
 * Created by jarek on 16.06.17.
 */
@Component
class UserInfo {

    fun auth(): Authentication {
        return SecurityContextHolder.getContext().getAuthentication()
    }

    fun principal(): User {
        return SecurityContextHolder.getContext().authentication.principal as User
    }
}