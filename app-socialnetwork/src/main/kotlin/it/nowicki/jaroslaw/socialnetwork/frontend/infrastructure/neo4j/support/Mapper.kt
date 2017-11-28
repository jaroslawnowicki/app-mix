package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.support

open interface Mapper<T, K> {

    fun mapToDTO(t: T?): K?

    fun map(k: K?): T?

}

