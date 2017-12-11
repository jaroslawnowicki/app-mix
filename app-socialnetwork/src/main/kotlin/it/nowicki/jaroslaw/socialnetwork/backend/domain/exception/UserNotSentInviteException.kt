package it.nowicki.jaroslaw.socialnetwork.backend.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

/**
 * Created by jarek on 16.06.17.
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "User current friend")
class UserNotSentInviteException : RuntimeException()