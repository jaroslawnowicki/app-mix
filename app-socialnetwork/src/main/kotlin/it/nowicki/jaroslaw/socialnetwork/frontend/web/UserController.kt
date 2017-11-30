package it.nowicki.jaroslaw.socialnetwork.frontend.web


import it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception.UserNotFoundException
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.response.OkResponse
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.UserService
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.security.UserInfo
import it.nowicki.jaroslaw.socialnetwork.frontend.web.handler.UserRequestHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(val userService: UserService, val userInfo: UserInfo, val userRequestHandler: UserRequestHandler) {

    val log = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping(value = "/")
    fun get(): User? {

        println(userInfo.email())
        log.debug(userInfo.email())
        return userService.getByUserId(userInfo.email())
    }

    @PostMapping("")
    fun create(@RequestBody user: UserRequestHandler.Request): OkResponse {
        userRequestHandler.createHandle(user)
        return OkResponse()
    }

    @PatchMapping("")
    fun update(@RequestBody user: UserRequestHandler.Request): OkResponse {
        user.user.userId = userInfo.principal().username ?: throw UserNotFoundException()
        userRequestHandler.updateHandle(user)
        return OkResponse()
    }

    @DeleteMapping("/{id}/delete")
    fun delete(@PathVariable id: String): OkResponse {
        userService.delete(id)
        return OkResponse()
    }

    @PatchMapping("/active/{userId}")
    fun active(@PathVariable userId: String): OkResponse {
        userId != userInfo.principal().username ?: throw UserNotFoundException()
        userService.active(userId)
        return OkResponse()
    }

    @GetMapping("/search/{text}")
    fun search(@PathVariable text: String): Set<User?> = userService.search(text)

}