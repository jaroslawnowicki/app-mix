package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.rabbitmq

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.Notification
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.NotificationMessage
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository

@Repository
class RabbitNotificationMessage @Autowired constructor(val amqpTemplate: AmqpTemplate) : NotificationMessage {

    val NOTIFICATION_QUEUE_NAME = "notification"

    override fun send(notification: Notification) {

        println("rabbit send")
        amqpTemplate.convertAndSend(NOTIFICATION_QUEUE_NAME, notification)
    }

}