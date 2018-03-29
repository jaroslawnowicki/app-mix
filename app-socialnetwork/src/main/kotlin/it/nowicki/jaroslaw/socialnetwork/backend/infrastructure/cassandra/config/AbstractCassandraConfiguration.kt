package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra.config

import org.springframework.cassandra.config.java.AbstractClusterConfiguration
import org.springframework.cassandra.core.keyspace.CreateKeyspaceSpecification
import org.springframework.cassandra.core.keyspace.DropKeyspaceSpecification
import org.springframework.cassandra.core.keyspace.KeyspaceOption


abstract class AbstractCassandraConfiguration : AbstractClusterConfiguration() {

    private val socialNetworkNamespace: String = "cover_typee"

    override fun getKeyspaceCreations(): List<CreateKeyspaceSpecification> {

        val specification = CreateKeyspaceSpecification.createKeyspace(socialNetworkNamespace)
                .with(KeyspaceOption.DURABLE_WRITES, true)
//                .withNetworkReplication(DataCenterReplication.dcr("rep1", 1))

        return arrayListOf(specification)
    }

    override fun getKeyspaceDrops(): List<DropKeyspaceSpecification> {
        return arrayListOf(DropKeyspaceSpecification.dropKeyspace(socialNetworkNamespace))
    }


    fun getKeyspaceName(): String {
        return socialNetworkNamespace
    }

    fun getEntityBasePackages(): Array<String> {
        return arrayOf("it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra")
    }


}