package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.post

import it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.user.UserDto
import org.neo4j.ogm.annotation.GraphId
import org.neo4j.ogm.annotation.NodeEntity

@NodeEntity(label = "Post")
class PostDto {

    @GraphId
    var id: Long? = null

    var title: String = ""

    var description: String = ""

    var createStamp: Long? = null

    var tags: Set<String> = HashSet()

    var timeChangeLog: Set<Long> = HashSet()

    var scope: Set<String> = HashSet()
    //
    var owner: UserDto? = null
////
//    var like: Set<UserDto> = HashSet()
}