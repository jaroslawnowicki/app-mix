package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra.covertype

import com.datastax.driver.core.DataType
import org.springframework.data.cassandra.mapping.CassandraType
import org.springframework.data.cassandra.mapping.PrimaryKey
import org.springframework.data.cassandra.mapping.Table
import java.io.Serializable
import java.util.*


@Table("cover_type")
class CoverTypeDTO : Serializable {


    @PrimaryKey
    @CassandraType(type = DataType.Name.UUID)
    var id: UUID? = null

    @CassandraType(type = DataType.Name.VARINT)
    var documentId: Int? = null

    @CassandraType(type = DataType.Name.VARINT)
    var num1: Int? = null

    @CassandraType(type = DataType.Name.VARCHAR)
    var num2: String? = null
}