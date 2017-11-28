package it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.aspect

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.Comment
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.NotificationService
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.Post
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component


@Aspect
@Component
class NotificationAspect @Autowired constructor(val notificationService: NotificationService) {

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.UserService.addInvite(String, String)) && args(userId, userInviteId)")
    @Throws(Throwable::class)
    fun addInvite(userId: String, userInviteId: String) {
        notificationService.addInvite(userId, userInviteId)
    }


    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.UserService.addFollow(String, String)) && args(userId, userIdFollow)")
    @Throws(Throwable::class)
    fun addFollow(userId: String, userIdFollow: String) {
        notificationService.addFollow(userId, userIdFollow)
    }

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.user.UserService.addFriend(String, String)) && args(userId, userIdFriend)")
    fun addFriend(userId: String, userIdFriend: String) {
        notificationService.addFriend(userId, userIdFriend)
    }

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.PostService.create(String, it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.Post)) && args(userId, post)")
    @Throws(Throwable::class)
    fun addPost(userId: String, post: Post) {
        notificationService.addPost(userId)
    }

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.PostService.update(String, it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.Post)) && args(userId, post)")
    @Throws(Throwable::class)
    fun updatePost(userId: String, post: Post) {
        notificationService.updatePost(userId)
    }

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.PostService.like(String, Long)) && args(userId, postId)")
    @Throws(Throwable::class)
    fun like(userId: String, postId: Long?) {
        notificationService.likePost(userId)
    }

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.post.PostService.repost(String, Long)) && args(userId, postId)")
    @Throws(Throwable::class)
    fun repost(userId: String, postId: Long?) {
        notificationService.repost(userId)
    }

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.CommentService.create(String, Long, it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.Comment)) && args(userId, postId, comment)")
    @Throws(Throwable::class)
    fun addComment(userId: String, postId: Long?, comment: Comment) {
        notificationService.addComment(userId)
    }

    @AfterReturning("execution(* it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.CommentService.update(String, Long, it.nowicki.jaroslaw.socialnetwork.frontend.domain.comment.Comment)) && args(userId, postId, com)")
    @Throws(Throwable::class)
    fun updateComment(userId: String, postId: Long?, com: Comment) {
        notificationService.updateComment(userId)
    }

}