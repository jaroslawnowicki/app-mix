package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.kafka

import it.nowicki.jaroslaw.socialnetwork.backend.domain.notification.Notification
import it.nowicki.jaroslaw.socialnetwork.backend.domain.notification.NotificationMessage
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class KafkaNotificationMessage @Autowired constructor(val kafkaProducer: KafkaProducer<String, Notification>) : NotificationMessage {

    val log = LoggerFactory.getLogger(KafkaNotificationMessage::class.java)

    @Value("\${config.kafka.topic}")
    lateinit var NOTIFICATION_KAFKA: String

    override fun send(notification: Notification) {
        log.info("Send message from Kafka")
        val record = ProducerRecord<String, Notification>(NOTIFICATION_KAFKA, notification)
        kafkaProducer.send(record)
    }
}