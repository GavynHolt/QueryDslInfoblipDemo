package com.gavynhholt.query_dsl_infoblip_demo.model.entity

import com.gavynhholt.query_dsl_infoblip_demo.model.enums.Instrument
import java.math.BigDecimal

data class MusicianSearchParams(
    val searchText: String? = null,
    val instruments: List<Instrument>? = null,
    val cities: List<String>? = null,
    val minYearsOfExperience: Int? = null,
    val maxYearsOfExperience: Int? = null,
    val minHourlyRate: BigDecimal? = null,
    val maxHourlyRate: BigDecimal? = null,
)
