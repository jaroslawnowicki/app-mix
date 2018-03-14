package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra.config

import org.springframework.context.annotation.Configuration

@Configuration
class CassandraConfiguration : AbstractCassandraConfiguration() {

    override fun setBeanClassLoader(classLoader: ClassLoader?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getStartupScripts(): List<String> {

        val script = ("CREATE KEYSPACE IF NOT EXISTS my_other_keyspace "
                + "WITH durable_writes = true "
                + "AND replication = { 'replication_factor' : 1, 'class' : 'SimpleStrategy' };")

        return arrayListOf(script)
    }

    override fun getShutdownScripts(): List<String> {
        return arrayListOf("DROP KEYSPACE my_other_keyspace;")
    }


}