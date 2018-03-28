package it.nowicki.jaroslaw.socialnetwork.backend.web

import it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype.CoverType
import it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype.CoverTypeService
import it.nowicki.jaroslaw.socialnetwork.backend.domain.response.OkResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by jarek on 28.03.18.
 */
@RestController
@RequestMapping("/test")
class TestController @Autowired constructor(val coverTypeService: CoverTypeService) {

    @GetMapping("/test")
    fun test(): OkResponse {

        println("dziala")
        val c = CoverType(1, 1)
        coverTypeService.save(c)

        return OkResponse()
    }
}