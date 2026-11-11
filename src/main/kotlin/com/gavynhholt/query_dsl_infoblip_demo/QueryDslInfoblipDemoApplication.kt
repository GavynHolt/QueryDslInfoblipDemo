package com.gavynhholt.query_dsl_infoblip_demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.web.config.EnableSpringDataWebSupport

@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
@SpringBootApplication
class QueryDslInfoblipDemoApplication

fun main(args: Array<String>) {
	runApplication<QueryDslInfoblipDemoApplication>(*args)
}
