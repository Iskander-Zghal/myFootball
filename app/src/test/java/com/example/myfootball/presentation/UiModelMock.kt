package com.example.myfootball.presentation

import com.example.myfootball.presentation.model.TeamDetailsUiModel
import com.example.myfootball.presentation.model.TeamUi

object UiModelMock {
    val teamsDisplayModel = listOf(
        TeamUi(
            teamName = "Rennes",
            teamLogo = "https://www.thesportsdb.com/images/media/team/badge/ypturx1473504818.png"
        ), TeamUi(
            teamName = "Paris SG",
            teamLogo = "https://www.thesportsdb.com/images/media/team/badge/rwqrrq1473504808.png"
        ), TeamUi(
            teamName = "Marseille",
            teamLogo = "https://www.thesportsdb.com/images/media/team/badge/uutsyt1473504764.png"
        )
    )

    val teamsDetailsDisplayModel = TeamDetailsUiModel(
        teamName = "Rennes",
        countryName = "France",
        leagueName = "French Ligue 1",
        descriptionTeam = "Le Stade rennais football club, couramment abrégé en Stade rennais, Stade rennais FC ou SRFC, est un club de football français fondé en 1901. Dans un premier temps club omnisports, il porte le nom de Stade rennais jusqu'à sa fusion avec le Football-club rennais en 1904, devenant alors le Stade rennais université-club. Il dispute ses premières compétitions officielles à partir de 1902 au sein du Comité de Bretagne de l'Union des sociétés françaises de sports athlétiques. En 1912, le club emménage sur un terrain situé au bord de la Vilaine, sur lequel est érigé l'actuel Stade de la route de Lorient.\r\n\r\nAprès avoir brillé dans les compétitions régionales, le Stade rennais se fait un nom au niveau national en atteignant la finale de la Coupe de France en 1922 et 1935. Au cours de cette période, en 1932, le club accède également au statut professionnel. Le stade rennais est l'un des membres fondateurs de la Première division du football français. Avec Marseille, Montpellier, Nice et Metz, Rennes est l'un des clubs qui a joué durant la saison inaugurale 1932-1933 et qui évolue encore en Ligue 1 aujourd'hui.\r\n\r\nDans les années 1960, il s'affirme, sous la houlette de son entraîneur Jean Prouff, comme l'une des meilleures équipes de l'hexagone, remportant par deux fois la Coupe de France en 1965 et 1971.\r\n\r\nCes bons résultats demeurent sans lendemain. Après avoir pris son indépendance vis-à-vis de la structure omnisports pour prendre son nom actuel en 1972, la section football connaît une longue traversée du désert sportive, ponctuée de graves difficultés financières. En 1998, la prise de contrôle de François Pinault et de sa holding Artémis transforme le club en société anonyme sportive professionnelle, permet sa modernisation, et le stabilise en Ligue 1. Il s'installe dans le haut de classement de première division, mais ne parvient pas à ajouter de nouvelles lignes à son palmarès, perdant deux finales de Coupe de France en 2009 et 2014 et une finale de Coupe de la Ligue, en 2013.",
        bannerImage = "https://www.thesportsdb.com/images/media/team/banner/tyvspt1423692142.jpg",
    )
}
