package it.nowicki.jaroslaw.socialnetwork.frontend.domain.post

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.media.Media
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.User


data class Post(var id: Long?, var title: String, var description: String, var createStamp: Long, var tags: Set<String>?, var timeChangeLog: Set<Long>?, var media: Set<Media>?,
                var likes: Set<User>?, var repost: Post?, var countLikes: Long?, var scope: Set<String>?, var owner: User?) : java.io.Serializable

