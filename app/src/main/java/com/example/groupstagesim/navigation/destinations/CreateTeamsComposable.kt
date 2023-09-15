package com.example.groupstagesim.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.groupstagesim.ui.screens.teams.CreateTeamsScreen
import com.example.groupstagesim.ui.viewmodels.TeamsViewModel
import com.example.groupstagesim.util.Constants.CREATE_TEAMS_SCREEN

fun NavGraphBuilder.createTeamsComposable(
    navigateToSimulateScreen: () -> Unit,
    teamsViewModel: TeamsViewModel
) {
    composable(
        route = CREATE_TEAMS_SCREEN
    ) {
        CreateTeamsScreen(
            teamsViewModel = teamsViewModel,
            navigateToSimulateScreen = navigateToSimulateScreen
        )
    }
}