package it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoverTypeService @Autowired constructor(private val coverTypeService: CoverTypeService) {

    fun save(coverType: CoverType) {
        coverTypeService.save(coverType)
    }
}