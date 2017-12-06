package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.kafka

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.Notification
import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.NotificationMessage
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Repository


/**
 * Created by jarek on 04.12.17.
 */
@Repository
@Primary
class KafkaNotificationMessage @Autowired constructor(val kafkaProducer: KafkaProducer<String, Notification>) : NotificationMessage {

    private val NOTIFICATION_KAFKA: String = "test"

    override fun send(notification: Notification) {
        println("kafka send")
        val record = ProducerRecord<String, Notification>(NOTIFICATION_KAFKA, notification)
        kafkaProducer.send(record)
    }
}