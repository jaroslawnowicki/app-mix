package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j.config

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

@Configuration
@EnableTransactionManagement
@ComponentScan("it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j")
@EnableNeo4jRepositories("it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.neo4j")
class Neo4jConfig