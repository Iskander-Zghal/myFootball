package com.example.myfootball.presentation

import com.example.myfootball.mockResponse.teams.TeamsMock.listToDisplay
import com.example.myfootball.presentation.UiModelMock.teamsDetailsDisplayModel
import com.example.myfootball.presentation.UiModelMock.teamsDisplayModel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class TeamTransformerTest {

    @InjectMocks
    private lateinit var teamTransformer: TeamTransformer

    @Test
    fun `transformTeamsToUiModel`() {
        // When
        val result = teamTransformer.transformTeamsToUiModel(team = listToDisplay)

        // Then

        assertThat(result).isEqualTo(teamsDisplayModel)
    }


    @Test
    fun `transformTeamDetailsUiModel`() {
        // When
        val result = teamTransformer.transformTeamDetailsUiModel(listToDisplay.first())

        // Then
        assertThat(result).isEqualTo(teamsDetailsDisplayModel)
    }
}