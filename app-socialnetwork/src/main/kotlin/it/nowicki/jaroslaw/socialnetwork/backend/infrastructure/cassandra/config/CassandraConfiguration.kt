package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra.config

import org.springframework.context.annotation.Configuration

@Configuration
class CassandraConfiguration : AbstractCassandraConfiguration() {

    override fun getStartupScripts(): List<String> {

        val script = ("CREATE KEYSPACE IF NOT EXISTS cover_type "
                + "WITH durable_writes = true "
                + "AND replication = { 'replication_factor' : 1, 'class' : 'SimpleStrategy' };")

        return arrayListOf(script)
    }

    override fun getShutdownScripts(): List<String> {
        return arrayListOf("DROP KEYSPACE cover_type;")
    }


}