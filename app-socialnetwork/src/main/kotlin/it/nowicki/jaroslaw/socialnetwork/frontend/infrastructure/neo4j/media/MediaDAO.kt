package it.nowicki.jaroslaw.socialnetwork.frontend.infrastructure.neo4j.media

import org.springframework.data.neo4j.repository.Neo4jRepository

/**
 * Created by jarek on 18.06.17.
 */
interface MediaDAO : Neo4jRepository<MediaDto, Long>
