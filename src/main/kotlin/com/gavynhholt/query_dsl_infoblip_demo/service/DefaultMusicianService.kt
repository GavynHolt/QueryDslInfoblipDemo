package com.gavynhholt.query_dsl_infoblip_demo.service

import com.gavynhholt.query_dsl_infoblip_demo.model.entity.Musician
import com.gavynhholt.query_dsl_infoblip_demo.model.entity.MusicianSearchParams
import com.gavynhholt.query_dsl_infoblip_demo.model.entity.QMusician
import com.gavynhholt.query_dsl_infoblip_demo.model.service.MusicianService
import com.gavynhholt.query_dsl_infoblip_demo.repository.MusicianRepository
import com.querydsl.core.BooleanBuilder
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.Assert
import java.util.UUID

@Service
class DefaultMusicianService(
    private val musicianRepository: MusicianRepository,
): MusicianService {
    override fun create(musician: Musician): Musician {
        // force ID to be null
        val toSave = musician.copy(id = null)
        return musicianRepository.save(toSave)
    }

    override fun getById(id: UUID): Musician {
        return musicianRepository.findByIdOrNull(id) ?: throw NoSuchElementException("Musician not found by id: $id")
    }

    override fun update(musician: Musician): Musician {
        Assert.notNull(musician.id, "id is required")

        return musicianRepository.save(musician)
    }

    override fun delete(id: UUID) {
        val qMusician = QMusician.musician

        val result = musicianRepository.update { query ->
            query.set(qMusician.deleted, true)
                .where(qMusician.id.eq(id), qMusician.deleted.isFalse()).execute()
        }

        if (result == 0L) {
            throw NoSuchElementException("Musician not found by id: $id")
        }
    }

    override fun search(params: MusicianSearchParams): List<Musician> {
        return search(params, Sort.unsorted())
    }

    override fun search(
        params: MusicianSearchParams,
        sort: Sort
    ): List<Musician> {
        return search(params, Pageable.unpaged(sort)).content
    }

    override fun search(
        params: MusicianSearchParams,
        pageable: Pageable
    ): Page<Musician> {
        val qMusician = QMusician.musician
        val builder = BooleanBuilder()

        if (!params.searchText.isNullOrBlank()) {
            // case-insensitive search of firstName and lastName
            val searchTerms = params.searchText
                .trim()
                // split by any number of whitespace chars
                .split(Regex("\\s+"))
                .filter { it.isNotBlank() }

            searchTerms.forEach { term ->
                builder.and(
                    qMusician.firstName.containsIgnoreCase(term)
                        .or(qMusician.lastName.containsIgnoreCase(term))
                )
            }
        }

        if (!params.instruments.isNullOrEmpty()) {
            builder.and(qMusician.instrument.`in`(params.instruments))
        }

        if (!params.cities.isNullOrEmpty()) {
            builder.and(qMusician.city.`in`(params.cities))
        }

        if (params.minYearsOfExperience != null) {
            builder.and(qMusician.hourlyRate.goe(params.minYearsOfExperience))
        }

        if (params.maxYearsOfExperience != null) {
            builder.and(qMusician.hourlyRate.loe(params.maxYearsOfExperience))
        }

        if (params.minHourlyRate != null) {
            builder.and(qMusician.hourlyRate.goe(params.minHourlyRate))
        }

        if (params.maxHourlyRate != null) {
            builder.and(qMusician.hourlyRate.loe(params.maxHourlyRate))
        }

        return musicianRepository.findAll(builder, pageable)
    }
}