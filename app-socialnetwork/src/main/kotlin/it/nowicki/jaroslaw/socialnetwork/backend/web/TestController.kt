package it.nowicki.jaroslaw.socialnetwork.backend.web

import it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype.CoverType
import it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype.CoverTypeService
import it.nowicki.jaroslaw.socialnetwork.backend.domain.response.OkResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@RequestMapping("/test")
class TestController @Autowired constructor(val coverTypeService: CoverTypeService) {

    @GetMapping("/test")
    fun test(): OkResponse {

        val c = CoverType(1, 1)
        coverTypeService.save(c)

        return OkResponse()
    }

    @GetMapping("/uuid/{id:[a-fA-F0-9-]*}")
    fun testId(@PathVariable("id") id: String): OkResponse {

        println("id " + id)
        val uuid = UUID.fromString(id)
        println("uuid" + uuid)
        println(coverTypeService.findOne(uuid))
        return OkResponse()
    }

}