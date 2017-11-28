package it.nowicki.jaroslaw.frontend

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController(value = "/index")
class IndexController {

    val log = LoggerFactory.getLogger(IndexController::class.java)

    @GetMapping
    fun index() : String {
        log.debug("test 1")
        log.info("test 2")

        if (1 == 1)
            throw RuntimeException("runtime exception")
        return "OK"
    }
}