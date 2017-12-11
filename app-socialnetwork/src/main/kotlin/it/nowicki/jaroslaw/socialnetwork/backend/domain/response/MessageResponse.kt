package it.nowicki.jaroslaw.socialnetwork.backend.domain.response

/**
 * Created by jarek on 16.06.17.
 */
open class MessageResponse {

    var message = ""

    var code: Int? = -1

    var time: Long? = 0L

    constructor() {}

    constructor(message: String) {
        this.message = message
    }

    constructor(message: String, code: Int?) {
        this.message = message
        this.code = code
    }

    constructor(message: String, code: Int?, time: Long?) {
        this.message = message
        this.code = code
        this.time = time
    }
}