package it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype

import java.util.*


interface CoverTypeRepository {

    fun save(coverType: CoverType): CoverType?

    fun findOne(id: UUID): CoverType?
}