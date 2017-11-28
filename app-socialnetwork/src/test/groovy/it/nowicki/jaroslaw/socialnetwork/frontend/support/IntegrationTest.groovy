package it.nowicki.jaroslaw.socialnetwork.frontend.support

import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMq
import io.arivera.oss.embedded.rabbitmq.EmbeddedRabbitMqConfig
import it.nowicki.jaroslaw.socialnetwork.SocialNetworkApplicationTest
import it.nowicki.jaroslaw.socialnetwork.frontend.config.PersistenceContextTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [SocialNetworkApplicationTest.class, PersistenceContextTest.class])
@ActiveProfiles("integration")
@Transactional
class IntegrationTest extends Specification {

    @Shared
    EmbeddedRabbitMq rabbitMq = null

    def setupSpec() {
        EmbeddedRabbitMqConfig config = new EmbeddedRabbitMqConfig.Builder().build()
        rabbitMq = new EmbeddedRabbitMq(config)
        rabbitMq.start()
    }

    def cleanupSpec() {
        rabbitMq.stop()
    }
}