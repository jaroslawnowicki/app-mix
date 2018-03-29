package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra.covertype

import com.datastax.driver.core.utils.UUIDs
import it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype.CoverType
import org.springframework.stereotype.Component

@Component
class CoverTypeDAOMapper {

    fun mapToDTO(coverType: CoverType): CoverTypeDTO {
        val coverTypeDTO = CoverTypeDTO()
        val uuid = UUIDs.timeBased()
        println(uuid)
        coverTypeDTO.id = uuid
        coverTypeDTO.documentId = coverType.id
        coverTypeDTO.num1 = coverType.num1
        coverTypeDTO.num2 = coverType.num1.toString()
        return coverTypeDTO
    }

    fun map(coverTypeDTO: CoverTypeDTO): CoverType? {

        if (null == coverTypeDTO)
            return null

        return CoverType(coverTypeDTO.documentId, coverTypeDTO.num1)
    }

}