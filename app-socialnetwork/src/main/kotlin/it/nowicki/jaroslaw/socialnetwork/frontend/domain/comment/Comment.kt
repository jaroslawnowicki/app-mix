package it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.User

data class Comment(var id: Long?, var post: Post?, var user: User?, var message: String?, var createStamp: Long?, var updateStamp: Long?) : java.io.Serializable
