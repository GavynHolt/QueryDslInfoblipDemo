package com.gavynhholt.query_dsl_infoblip_demo

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
	fromApplication<QueryDslInfoblipDemoApplication>().with(TestcontainersConfiguration::class).run(*args)
}
