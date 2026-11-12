package com.gavynhholt.query_dsl_infoblip_demo.service

import com.gavynhholt.query_dsl_infoblip_demo.TestcontainersConfiguration
import com.gavynhholt.query_dsl_infoblip_demo.model.entity.MusicianSearchParams
import com.gavynhholt.query_dsl_infoblip_demo.model.enums.Instrument
import com.gavynhholt.query_dsl_infoblip_demo.repository.MusicianRepository
import com.github.database.rider.core.api.configuration.DBUnit
import com.github.database.rider.core.api.dataset.DataSet
import com.github.database.rider.junit5.api.DBRider
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Sort

@SpringBootTest
@DBRider
@DBUnit(
    schema = "public",
    caseSensitiveTableNames = true
)
@Import(TestcontainersConfiguration::class)
class DefaultMusicianServiceIntegrationTest(
    @Autowired val service: DefaultMusicianService,
    @Autowired val musicianRepository: MusicianRepository
) {
    @Test
    fun smokeTest() {
        assertThat(service).isNotNull
        assertThat(musicianRepository).isNotNull
    }

    @Test
    @DataSet("musicians.yml")
    fun searchMusiciansListWithSortAscending_shouldReturnSortedList() {
        // arrange
        val params = MusicianSearchParams(instruments = listOf(Instrument.PIANO, Instrument.DRUMS, Instrument.GUITAR))
        val sort = Sort.by(Sort.Direction.ASC, "firstName")

        // act
        val result = service.search(params, sort)

        // assert
        assertThat(result)
            .hasSize(4)
            .extracting<String> { it.firstName }
            .containsExactly(
                "Antonio",
                "Chick",
                "Pat",
                "Roy"
            )
    }

    @Test
    @DataSet("musicians.yml")
    fun searchMusiciansListWithSortDescending_shouldReturnSortedList() {
        // arrange
        val params = MusicianSearchParams(instruments = listOf(Instrument.PIANO, Instrument.DRUMS, Instrument.GUITAR))
        val sort = Sort.by(Sort.Direction.DESC, "firstName")

        // act
        val result = service.search(params, sort)

        // assert
        assertThat(result)
            .hasSize(4)
            .extracting<String> { it.firstName }
            .containsExactly(
                "Roy",
                "Pat",
                "Chick",
                "Antonio"
            )
    }
}