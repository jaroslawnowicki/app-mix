package it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype


interface CoverTypeRepository {

    fun save(coverType: CoverType): CoverType?
}