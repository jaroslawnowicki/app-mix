package it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class NotificationService @Autowired constructor(val notificationMessage: NotificationMessage) {

    fun addInvite(userId: String, userInviteId: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.INVITE_SENT))
    }

    fun addFollow(userId: String, userIdFollow: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.FOLLOW))
    }

    fun addFriend(userId: String, userIdFriend: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.FRIEND_ADDED))
    }

    fun addPost(userId: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.POST_ADDED))
    }

    fun updatePost(userId: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.POST_UPDATE))
    }

    fun likePost(userId: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.POST_LIKE))
    }

    fun repost(userId: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.POST_REPOST))
    }

    fun addComment(userId: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.COMMENT_NEW))
    }

    fun updateComment(userId: String) {
        notificationMessage.send(this.createAction(userId, NotificationType.COMMENT_UPDATE))
    }

    private fun createAction(userId: String, type: NotificationType): Notification = Notification(userId, type, LocalDateTime.now())
}