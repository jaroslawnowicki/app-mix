package it.nowicki.jaroslaw.socialnetwork.frontend.config

import org.neo4j.ogm.session.SessionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Profile
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement

@org.springframework.context.annotation.Configuration
@EnableTransactionManagement
@ComponentScan("it.nowicki.jaroslaw.socialnetwork")
@EnableNeo4jRepositories("it.nowicki.jaroslaw.socialnetwork")
@Profile("integration")
class PersistenceContextTest {

    @Bean
    org.neo4j.ogm.config.Configuration configuration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration()
        configuration.driverConfiguration().setURI("file:////home/jarek/var/tmp/graph.db")
        return configuration
    }

    @Bean
    SessionFactory sessionFactory() {
        return new SessionFactory(configuration(), "it.nowicki.jaroslaw.socialnetwork")
    }

    @Bean
    PlatformTransactionManager transactionManager() {
        return new Neo4jTransactionManager(sessionFactory())
    }
}


