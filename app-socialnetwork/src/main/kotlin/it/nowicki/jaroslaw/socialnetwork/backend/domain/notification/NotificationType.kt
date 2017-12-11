package it.nowicki.jaroslaw.socialnetwork.backend.domain.notification


enum class NotificationType {
    INVITE_SENT,
    FOLLOW,
    FRIEND_ADDED,
    POST_ADDED,
    POST_UPDATE,
    POST_LIKE,
    POST_REPOST,
    COMMENT_NEW,
    COMMENT_UPDATE
}