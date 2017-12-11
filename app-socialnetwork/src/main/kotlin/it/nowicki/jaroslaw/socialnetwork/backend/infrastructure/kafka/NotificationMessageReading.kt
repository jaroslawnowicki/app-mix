package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.kafka

import it.nowicki.jaroslaw.socialnetwork.backend.domain.notification.NotificationType


class NotificationMessageReading constructor(val userId: String?, val notificationType: NotificationType, val created: Long?)