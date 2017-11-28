package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.comment

import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.post.PostDto
import it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.user.UserDto
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

@NodeEntity(label = "Comment")
class CommentDto {

    @GraphId
    var id: Long? = null

    @Relationship(type = "COMMENT", direction = Relationship.OUTGOING)
    var post: PostDto? = null

    @Relationship(type = "COMMENT_OWNER", direction = Relationship.OUTGOING)
    var user: UserDto? = null

    var message: String? = ""

    var createStamp: Long? = 0L

    var updateStamp: Long? = 0L
}