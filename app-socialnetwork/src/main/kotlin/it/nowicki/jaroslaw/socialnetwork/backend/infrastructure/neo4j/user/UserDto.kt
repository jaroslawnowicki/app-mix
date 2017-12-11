package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.user

import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.Index
import org.neo4j.ogm.annotation.NodeEntity
import org.neo4j.ogm.annotation.Relationship

/**
 * Created by jarek on 17.06.17.
 */
@NodeEntity(label = "User")
class UserDto {

    @GraphId
    var id: Long? = null

    @Index(unique = true, primary = true)
    var userId: String? = null

    var isActive: Boolean? = false

    var createStamp: Long? = 0

    var email: String? = ""

    var name: String? = ""

    var nickName: String? = ""

//	@Relationship(type = "POST", direction = Relationship.OUTGOING)
//	var posts: Set<PostDto> = HashSet()

    @Relationship(type = "INVITE", direction = Relationship.OUTGOING)
    var invites: Set<UserDto> = HashSet()

    @Relationship(type = "FRIEND", direction = Relationship.OUTGOING)
    var friends: Set<UserDto> = HashSet()
    //
    @Relationship(type = "FOLLOW", direction = Relationship.OUTGOING)
    var follows: Set<UserDto> = HashSet()
//
//	@Relationship(type = "FOLLOW", direction = Relationship.INCOMING)
//	var followers: Set<UserDto?>? = HashSet()
//
//	@Relationship(type = "LIKE", direction = Relationship.OUTGOING)
//	var likes: Set<PostDto?>?  = HashSet()
//
//	var comments:  Set<CommentDto> = HashSet()

    var avatarNameFile: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserDto

        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        return userId?.hashCode() ?: 0
    }


}