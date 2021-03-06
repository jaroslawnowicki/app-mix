package it.nowicki.jaroslaw.socialnetwork.backend.web


import it.nowicki.jaroslaw.socialnetwork.backend.domain.exception.UserNotFoundException
import it.nowicki.jaroslaw.socialnetwork.backend.domain.response.OkResponse
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.User
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.UserService
import it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.security.UserInfo
import it.nowicki.jaroslaw.socialnetwork.backend.web.handler.UserRequestHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController @Autowired constructor(val userService: UserService, val userInfo: UserInfo, val userRequestHandler: UserRequestHandler) {

    val log = LoggerFactory.getLogger(UserController::class.java)

    @GetMapping(value = "/")
    fun get(): User? {
        try {
            return userService.getByUserId(userInfo.email())
        } catch (ex: UserNotFoundException) {
            val user = User(null, userInfo.email(), false, null, userInfo.email(), userInfo.name(), userInfo.name(), null, null, null)
            log.info("Create user {}", userInfo.email())
            return userService.create(user)
        }
        return null
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