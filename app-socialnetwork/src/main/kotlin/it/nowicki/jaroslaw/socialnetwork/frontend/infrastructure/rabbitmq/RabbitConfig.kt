package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.rabbitmq

import org.springframework.amqp.core.*
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitConfig {

    val NOTIFICATION_QUEUE_NAME = "notification"

    @Bean
    fun notificationQueue(): Queue = Queue(NOTIFICATION_QUEUE_NAME, false)

    @Bean
    fun topicExchange(): TopicExchange = TopicExchange("notification-exchange-topic")

    @Bean
    fun directExchange(): DirectExchange = DirectExchange("notification-exchange-direct")

    @Bean
    fun notificationBinding(): Binding = BindingBuilder.bind(notificationQueue()).to(directExchange()).withQueueName()

    @Bean
    fun messageConverter(): MessageConverter = Jackson2JsonMessageConverter()
}