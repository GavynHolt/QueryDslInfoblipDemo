package com.gavynhholt.query_dsl_infoblip_demo.repository

import com.gavynhholt.query_dsl_infoblip_demo.model.entity.Musician
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface MusicianRepository: QuerydslJdbcRepository<Musician, UUID> {
}