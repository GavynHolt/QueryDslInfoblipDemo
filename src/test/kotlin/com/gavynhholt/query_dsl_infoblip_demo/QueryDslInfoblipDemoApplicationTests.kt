package com.gavynhholt.query_dsl_infoblip_demo

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class QueryDslInfoblipDemoApplicationTests {

	@Test
	fun contextLoads() {
	}
}
