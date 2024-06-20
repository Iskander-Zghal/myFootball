package com.example.myfootball.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.myfootball.R
import com.example.myfootball.presentation.model.TeamDetailsUiModel
import com.example.myfootball.presentation.model.TeamDetailsViewState

@Composable
fun TeamDetailsScreenContent(
    teamsViewModel: TeamsViewModel,

    ) {
    val teamDetailsState by teamsViewModel.teamDetailsViewState.collectAsStateWithLifecycle()

    var team by remember { mutableStateOf(TeamDetailsUiModel()) }

    LaunchedEffect(key1 = teamDetailsState) {
        when (val details = teamDetailsState) {
            TeamDetailsViewState.Idle -> Unit
            is TeamDetailsViewState.Ready -> team = details.data
            TeamDetailsViewState.Empty -> Unit
        }
    }

    TeamItemContent(team = team)
}

@Composable
private fun TeamItemContent(team: TeamDetailsUiModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 48.dp)
                .padding(horizontal = 48.dp),
            alignment = Alignment.Center,
            model = ImageRequest.Builder(LocalContext.current)
                .data(team.bannerImage)
                .crossfade(true)
                .build(),
            placeholder = painterResource(id = R.drawable.team_placeholder),
            contentDescription = null,
        )
        team.teamName?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = it,
                textAlign = TextAlign.Center,
                fontSize = 18.sp
            )
        }
        team.leagueName?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp),
                text = stringResource(
                    id = R.string.team_league,
                    it
                ),
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
        }

        team.descriptionTeam?.let {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                text = stringResource(id = R.string.team_description, it),
                textAlign = TextAlign.Start,
                fontSize = 18.sp
            )
        }
    }
}


@Preview("TeamItemContent")
@Composable
fun TeamItemContentPreview() {
    TeamItemContent(
        TeamDetailsUiModel(
            teamName = "Arsenal",
            bannerImage = "https://www.thesportsdb.com/images/media/team/badge/n06q811667936857.png",
            countryName = "ARS",
            descriptionTeam = "Arsenal Football Club, communément appelé Arsenal, est un club anglais de football, fondé le 1er décembre 1886 à Londres. Son siège est situé dans le borough londonien d'Islington, au nord de la capitale britannique."
        )
    )
}
