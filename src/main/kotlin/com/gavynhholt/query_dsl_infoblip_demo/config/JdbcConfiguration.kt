package com.gavynhholt.query_dsl_infoblip_demo.config

import com.gavynhholt.query_dsl_infoblip_demo.model.enums.Instrument
import com.querydsl.sql.PostgreSQLTemplates
import com.querydsl.sql.types.EnumByNameType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import com.querydsl.sql.Configuration as QueryDslConfiguration

@Configuration
class JdbcConfiguration {

    @Bean
    fun queryDslConfiguration(): QueryDslConfiguration {
        return QueryDslConfiguration(PostgreSQLTemplates()).apply {
            register(EnumByNameType(Instrument::class.java))
        }
    }
}