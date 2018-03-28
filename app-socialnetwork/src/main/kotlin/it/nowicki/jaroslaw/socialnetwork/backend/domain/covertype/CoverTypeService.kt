package it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CoverTypeService {

    @Autowired
    lateinit var coverTypeRepository: CoverTypeRepository

    fun save(coverType: CoverType) {
        coverTypeRepository.save(coverType)
    }
}