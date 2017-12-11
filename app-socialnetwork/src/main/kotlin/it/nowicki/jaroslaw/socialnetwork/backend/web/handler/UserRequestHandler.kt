package it.nowicki.jaroslaw.socialnetwork.backend.web.handler

import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.UserService
import org.springframework.stereotype.Component

/**
 * Created by jarek on 20.09.17.
 */
@Component
class UserRequestHandler(private val userService: UserService) {

    data class Request(val user: User)


    fun createHandle(request: Request) {
        userService.create(request.user)
    }

    fun updateHandle(request: Request) {
        userService.update(request.user)
    }
}