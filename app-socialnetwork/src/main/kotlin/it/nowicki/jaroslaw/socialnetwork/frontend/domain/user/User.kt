package it.nowicki.jaroslaw.socialnetwork.frontend.domain.user

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder

/**
 * Created by jarek on 15.06.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(alphabetic = true)
data class User(var id: Long?, var userId: String?, var isActive: Boolean?, var createStamp: Long?, var email: String?, var name: String?, var nickName: String?,
//                 var posts: Set<Post> = HashSet(),
                var invites: Set<User>?,
                var friends: Set<User>?,
                var follows: Set<User>?
//                var followers: Set<User>
//                 var likes: Set<Post>
) : java.io.Serializable
