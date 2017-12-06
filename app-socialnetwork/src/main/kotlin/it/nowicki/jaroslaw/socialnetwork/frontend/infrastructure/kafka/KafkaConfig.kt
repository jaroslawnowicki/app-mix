package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.kafka

import it.nowicki.jaroslaw.socialnetwork.frontend.domain.notification.Notification
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.common.serialization.StringSerializer
//import org.apache.kafka.common.serialization.
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*
import kotlin.reflect.jvm.jvmName

/**
 * Created by jarek on 05.12.17.
 */
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

    @Value("\${config.kafka.serializer.key}")
    lateinit var keySerializerValue: String

    @Value("\${config.kafka.serializer.value}")
    lateinit var valueSerializerValue: String

    @Bean
    fun create(): KafkaProducer<String, Notification> {
        return KafkaProducer<String, Notification>(config())
    }

    private fun config(): Properties {
        val props = Properties()
        props.put(BOOTSTRAP_SERVERS_KEY, bootstrapServersValue)
        props.put(CLIENT_ID_KEY, clientId)
        props.put(KEY_SERIALIZER_KEY, StringSerializer::class.jvmName)
//        props.put(VALUE_SERIALIZER_KEY, JsonSerializer)
        return props
    }
}