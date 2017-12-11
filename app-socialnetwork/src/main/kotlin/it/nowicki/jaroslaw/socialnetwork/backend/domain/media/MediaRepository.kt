package it.nowicki.jaroslaw.socialnetwork.backend.domain.media

interface MediaRepository {

    fun save(media: Media): Media?

    fun delete(id: Long?)

    fun deleteAll()

    fun getById(id: Long?): Media?

}
