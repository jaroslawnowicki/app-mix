package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra.covertype

import it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype.CoverType
import it.nowicki.jaroslaw.socialnetwork.backend.domain.covertype.CoverTypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class CassandraCoverTypeRepository @Autowired
constructor(val coverTypeDAOMapper: CoverTypeDAOMapper, val coverTypeDAO: CoverTypeDAO) : CoverTypeRepository {


    override fun save(coverType: CoverType): CoverType? {
        return coverTypeDAOMapper.map(coverTypeDAO.save(coverTypeDAOMapper.mapToDTO(coverType)))
    }

    override fun findOne(id: UUID): CoverType? {
        val res = coverTypeDAO.findOne(id)
        return coverTypeDAOMapper.map(res)
    }

}