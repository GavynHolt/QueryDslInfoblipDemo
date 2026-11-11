package com.gavynhholt.query_dsl_infoblip_demo.rest

import com.gavynhholt.query_dsl_infoblip_demo.model.entity.Musician
import com.gavynhholt.query_dsl_infoblip_demo.model.entity.MusicianSearchParams
import com.gavynhholt.query_dsl_infoblip_demo.model.enums.Instrument
import com.gavynhholt.query_dsl_infoblip_demo.model.service.MusicianService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.util.UUID

@RestController
@RequestMapping("/musician")
class MusicianRestController(
    val musicianService: MusicianService
) {

    @GetMapping("{id}")
    fun get(@PathVariable("id") id: UUID): Musician {
        return musicianService.getById(id)
    }

    @GetMapping("search/page")
    fun searchPage(
        @RequestParam(required = false) searchText: String?,
        @RequestParam(required = false) instruments: List<Instrument>?,
        @RequestParam(required = false) cities: List<String>?,
        @RequestParam(required = false) minYearsOfExperience: Int?,
        @RequestParam(required = false) maxYearsOfExperience: Int?,
        @RequestParam(required = false) minHourlyRate: BigDecimal?,
        @RequestParam(required = false) maxHourlyRate: BigDecimal?,
        @RequestParam(defaultValue = "firstName") sortProperty: String,
        @RequestParam(defaultValue = "ASC") sortDirection: Sort.Direction,
        @RequestParam(defaultValue = "0") pageNumber: Int,
        @RequestParam(defaultValue = "10") pageSize: Int,
        ): Page<Musician> {

        val allowedSortProperties = setOf(
            "firstName",
            "lastName",
            "city",
            "yearsOfExperience",
            "hourlyRate",
        )

        require(sortProperty in allowedSortProperties) {
            "Unsupported sort property: $sortProperty"
        }

        val params = MusicianSearchParams(
            searchText = searchText,
            instruments = instruments,
            cities = cities,
            minYearsOfExperience = minYearsOfExperience,
            maxYearsOfExperience = maxYearsOfExperience,
            minHourlyRate = minHourlyRate,
            maxHourlyRate = maxHourlyRate,
        )

        val sort = Sort.by(sortDirection, sortProperty)
        val pageable = PageRequest.of(pageNumber, pageSize, sort)

        return musicianService.search(params, pageable)
    }

    @GetMapping("search/list")
    fun searchList(
        @RequestParam(required = false) searchText: String?,
        @RequestParam(required = false) instruments: List<Instrument>?,
        @RequestParam(required = false) cities: List<String>?,
        @RequestParam(required = false) minYearsOfExperience: Int?,
        @RequestParam(required = false) maxYearsOfExperience: Int?,
        @RequestParam(required = false) minHourlyRate: BigDecimal?,
        @RequestParam(required = false) maxHourlyRate: BigDecimal?,
        @RequestParam(defaultValue = "firstName") sortProperty: String,
        @RequestParam(defaultValue = "ASC") sortDirection: Sort.Direction,
    ): List<Musician> {
        val allowedSortProperties = setOf(
            "firstName",
            "lastName",
            "city",
            "yearsOfExperience",
            "hourlyRate",
        )

        require(sortProperty in allowedSortProperties) {
            "Unsupported sort property: $sortProperty"
        }

        val params = MusicianSearchParams(
            searchText = searchText,
            instruments = instruments,
            cities = cities,
            minYearsOfExperience = minYearsOfExperience,
            maxYearsOfExperience = maxYearsOfExperience,
            minHourlyRate = minHourlyRate,
            maxHourlyRate = maxHourlyRate,
        )

        val sort = Sort.by(sortDirection, sortProperty)

        return musicianService.search(params, sort)
    }
}