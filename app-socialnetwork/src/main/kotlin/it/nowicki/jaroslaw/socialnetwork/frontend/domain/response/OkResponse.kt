package it.nowicki.jaroslaw.socialnetwork.frontend.domain.response

/**
 * Created by jarek on 16.06.17.
 */
class OkResponse : MessageResponse(OkResponse.message) {
    companion object {
        private val message = "OK"
    }
}