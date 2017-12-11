package it.nowicki.jaroslaw.socialnetwork.backend.domain.media

import it.nowicki.jaroslaw.socialnetwork.backend.domain.post.Post
import it.nowicki.jaroslaw.socialnetwork.backend.domain.user.User

data class Media(var id: Long?, var blob: String?, var type: String?, var createStamp: Long?, var path: String?, var user: User?, var post: Post?)
