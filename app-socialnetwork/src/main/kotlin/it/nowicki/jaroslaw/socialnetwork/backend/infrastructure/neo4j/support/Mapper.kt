package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.support

open interface Mapper<T, K> {

    fun mapToDTO(t: T?): K?

    fun map(k: K?): T?

}

