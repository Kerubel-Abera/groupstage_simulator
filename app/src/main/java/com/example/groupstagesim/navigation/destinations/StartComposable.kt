package com.example.groupstagesim.navigation.destinations

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.groupstagesim.ui.screens.start.StartScreen
import com.example.groupstagesim.ui.viewmodels.TeamsViewModel
import com.example.groupstagesim.util.Constants.START_SCREEN

fun NavGraphBuilder.startComposable(
    navigateToCreateTeamsScreen: () -> Unit,
    teamsViewModel: TeamsViewModel
) {
    composable(
        route = START_SCREEN
    ) {
        StartScreen(navigateToCreateTeamsScreen, teamsViewModel)
    }
}