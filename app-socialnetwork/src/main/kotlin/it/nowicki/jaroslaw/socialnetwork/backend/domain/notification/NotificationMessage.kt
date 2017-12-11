package it.nowicki.jaroslaw.socialnetwork.backend.domain.notification

interface NotificationMessage {

    fun send(notification: Notification)
}