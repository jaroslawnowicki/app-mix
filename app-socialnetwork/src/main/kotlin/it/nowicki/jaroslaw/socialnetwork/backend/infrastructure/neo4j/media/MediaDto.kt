package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.media

import it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.post.PostDto
import it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.user.UserDto
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

/**
 * Created by jarek on 18.06.17.
 */
@NodeEntity(label = "Media")
open class MediaDto {

    @GraphId
    var id: Long? = null

    var path: String? = null

    var type: String? = null

    var createStamp: Long? = null

    @Relationship(type = "MEDIA", direction = Relationship.INCOMING)
    var post: PostDto? = null

    @Relationship(type = "MEDIA_OWNER", direction = Relationship.INCOMING)
    var owner: UserDto? = null
}