package it.nowicki.jaroslaw.socialnetwork.backend.domain.comment

interface CommentRepository {

    fun save(comment: Comment): Comment?

    fun delete(commentId: Long?)

    fun getById(commentId: Long?): Comment?

    fun deleteAll()

}