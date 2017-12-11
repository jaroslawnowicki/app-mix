package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.kafka

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.Notification
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import kotlin.reflect.jvm.jvmName

@Configuration
class KafkaConfig {

    private val BOOTSTRAP_SERVERS_KEY = "bootstrap.servers"
    private val CLIENT_ID_KEY = "client.id"
    private val KEY_SERIALIZER_KEY = "key.serializer"
    private val VALUE_SERIALIZER_KEY = "value.serializer"

    @Value("\${config.kafka.bootstrap-servers}")
    lateinit var bootstrapServersValue: String

    @Value("\${config.kafka.client.id}")
    lateinit var clientId: String

    @Bean
    fun create(): KafkaProducer<String, Notification> =
            KafkaProducer<String, Notification>(config())

    private fun config(): Properties {
        val props = Properties()
        props.put(BOOTSTRAP_SERVERS_KEY, bootstrapServersValue)
        props.put(CLIENT_ID_KEY, clientId)
        props.put(KEY_SERIALIZER_KEY, StringSerializer::class.jvmName)
        props.put(VALUE_SERIALIZER_KEY, KryoReadingSerializer::class.jvmName)
        return props
    }
}