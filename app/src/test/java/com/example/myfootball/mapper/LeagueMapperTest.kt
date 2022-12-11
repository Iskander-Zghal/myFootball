package com.example.myfootball.mapper

import com.example.myfootball.data.mapper.LeagueMapper
import com.example.myfootball.mockResponse.leagues.LeaguesMock.leagues
import com.example.myfootball.mockResponse.leagues.LeaguesResponseMock.leaguesResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class LeagueMapperTest {

    @InjectMocks
    private lateinit var mapper: LeagueMapper

    @Test
    fun `transformLeagueResponseToLeague when should return list of leagues`() {
        // When
        val result =
            mapper.transformLeagueResponseToLeague(leaguesResponse)

        // Then
        assertThat(result).isEqualTo(leagues)
    }
}