package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.rabbitmq

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.Notification
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.NotificationMessage
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class RabbitNotificationMessage @Autowired constructor(val amqpTemplate: AmqpTemplate) : NotificationMessage {

    val log = LoggerFactory.getLogger(RabbitNotificationMessage::class.java)

    @Value("\${config.rabbitmq.queue}")
    lateinit var NOTIFICATION_QUEUE_NAME: String

    override fun send(notification: Notification) {
        log.info("Send notification form Rabbit")
        amqpTemplate.convertAndSend(NOTIFICATION_QUEUE_NAME, notification)
    }

}