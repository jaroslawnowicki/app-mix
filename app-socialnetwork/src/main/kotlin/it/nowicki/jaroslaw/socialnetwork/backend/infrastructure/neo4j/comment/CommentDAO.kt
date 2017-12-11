package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.comment

import org.springframework.data.neo4j.annotation.Query
import org.springframework.data.neo4j.repository.Neo4jRepository

interface CommentDAO : Neo4jRepository<CommentDto, Long> {

    @Query("MATCH (comment:Comment) - [owner:COMMENT_OWNER] -> (user:User) WHERE id(comment) = {0} return comment, owner, user")
    fun getById(id: Long?): CommentDto
}