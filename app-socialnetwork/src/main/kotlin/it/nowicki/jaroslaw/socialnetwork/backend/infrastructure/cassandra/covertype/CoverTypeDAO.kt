package it.nowicki.jaroslaw.socialnetwork.backend.infrastructure.cassandra.covertype

import org.springframework.data.repository.CrudRepository
import java.util.*

interface CoverTypeDAO : CrudRepository<CoverTypeDTO, UUID>