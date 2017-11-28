package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@ComponentScan("it.nowicki.jaroslaw.socialnetwork")
@EnableNeo4jRepositories("it.nowicki.jaroslaw.socialnetwork")
class Neo4jConfig