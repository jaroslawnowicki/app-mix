package it.nowicki.jaroslaw.socialnetwork.frontend.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Media not found")
class MediaNotFoundException : RuntimeException()