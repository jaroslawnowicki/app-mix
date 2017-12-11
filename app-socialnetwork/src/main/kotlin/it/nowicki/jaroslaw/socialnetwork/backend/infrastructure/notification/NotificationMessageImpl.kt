package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.notification

import it.nowicki.jaroslaw.socialnetwork.backend.domain.notification.Notification
import it.nowicki.jaroslaw.socialnetwork.backend.domain.notification.NotificationMessage
import it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.kafka.KafkaNotificationMessage
import it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.rabbitmq.RabbitNotificationMessage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository

@Repository
@Primary
class NotificationMessageImpl @Autowired constructor(val rabbitNotificationMessage: RabbitNotificationMessage, val kafkaNotificationMessage: KafkaNotificationMessage) : NotificationMessage {

    override fun send(notification: Notification) {
        rabbitNotificationMessage.send(notification)
        kafkaNotificationMessage.send(notification)
    }

}