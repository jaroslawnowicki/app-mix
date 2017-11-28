package it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification

interface NotificationMessage {

    fun send(notification: Notification)
}