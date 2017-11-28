package it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Comment not found")
class CommentNotFoundException : RuntimeException()