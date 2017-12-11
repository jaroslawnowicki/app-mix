package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.kafka

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.NotificationType


class NotificationMessageReading constructor(val userId: String?, val notificationType: NotificationType, val created: Long?)