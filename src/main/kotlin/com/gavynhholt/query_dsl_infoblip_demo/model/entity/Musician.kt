package com.gavynhholt.query_dsl_infoblip_demo.model.entity

import com.gavynhholt.query_dsl_infoblip_demo.model.enums.Instrument
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.math.BigDecimal
import java.util.UUID

@Table("musician")
data class Musician(
    @Id
    @Column("id")
    val id: UUID? = null,
    @Column("first_name")
    val firstName: String,
    @Column("last_name")
    val lastName: String,
    @Column("email")
    val email: String,
    @Column("city")
    val city: String,
    @Column("instrument")
    val instrument: Instrument,
    @Column("years_of_experience")
    val yearsOfExperience: Int,
    @Column("hourly_rate")
    val hourlyRate: BigDecimal,
    @Column("deleted")
    val deleted: Boolean = false,
)
