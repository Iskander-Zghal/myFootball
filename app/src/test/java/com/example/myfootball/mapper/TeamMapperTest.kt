package com.example.myfootball.mapper

import com.example.myfootball.data.mapper.TeamMapper
import com.example.myfootball.mockResponse.teams.TeamsMock.teams
import com.example.myfootball.mockResponse.teams.TeamsResponseMock.teamsResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class TeamMapperTest {

    @InjectMocks
    private lateinit var mapper: TeamMapper

    @Test
    fun `transformTeamsResponseToTeam when should return list of teams`() {
        // When
        val result =
            mapper.transformTeamsResponseToTeam(teamsResponse)

        // Then
        assertThat(result).isEqualTo(teams)
    }
}