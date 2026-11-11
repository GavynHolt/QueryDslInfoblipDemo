package com.gavynhholt.query_dsl_infoblip_demo.model.service

import com.gavynhholt.query_dsl_infoblip_demo.model.entity.Musician
import com.gavynhholt.query_dsl_infoblip_demo.model.entity.MusicianSearchParams
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import java.util.UUID

interface MusicianService {
    fun create(musician: Musician): Musician

    fun getById(id: UUID): Musician

    fun update(musician: Musician): Musician

    fun delete(id: UUID)

    fun search(params: MusicianSearchParams): List<Musician>

    fun search(params: MusicianSearchParams, sort: Sort): List<Musician>

    fun search(params: MusicianSearchParams, pageable: Pageable): Page<Musician>
}