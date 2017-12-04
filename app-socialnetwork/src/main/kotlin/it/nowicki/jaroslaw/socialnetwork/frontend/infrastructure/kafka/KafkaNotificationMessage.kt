package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.kafka

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.Notification
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cloud.stream.annotation.EnableBinding
import org.springframework.cloud.stream.messaging.Sink
import org.springframework.integration.kafka.core.KafkaTemplate

/**
 * Created by jarek on 04.12.17.
 */
@EnableBinding(Sink.class)
        class KafkaNotificationMessage @Autowired constructor(
val kafkaTemplate: KafkaTemplate<String, Notification> ) : NotificationMessage
{

    override fun send(notification: Notification) {
        kafkaTemplate.send()
    }


}