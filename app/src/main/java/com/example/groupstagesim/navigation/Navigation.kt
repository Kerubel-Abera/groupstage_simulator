package com.example.groupstagesim.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.groupstagesim.navigation.destinations.createTeamsComposable
import com.example.groupstagesim.navigation.destinations.resultsComposable
import com.example.groupstagesim.navigation.destinations.simulateComposable
import com.example.groupstagesim.navigation.destinations.startComposable
import com.example.groupstagesim.ui.viewmodels.ResultsViewModel
import com.example.groupstagesim.ui.viewmodels.SimulateViewModel
import com.example.groupstagesim.ui.viewmodels.TeamsViewModel
import com.example.groupstagesim.util.Constants.START_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    teamsViewModel: TeamsViewModel,
    resultsViewModel: ResultsViewModel,
    simulateViewModel: SimulateViewModel
) {
    val screen = Screens(navController)

    NavHost(
        navController = navController,
        startDestination = START_SCREEN
    ) {
        startComposable(
            navigateToCreateTeamsScreen = screen.createTeams,
            teamsViewModel = teamsViewModel
        )
        createTeamsComposable(
            navigateToSimulateScreen = screen.simulateMatch,
            teamsViewModel = teamsViewModel
        )
        simulateComposable(
            navigateToResultsScreen = screen.results,
            simulateViewModel = simulateViewModel
        )
        resultsComposable(
            navigateToStartScreen = screen.start,
            resultsViewModel = resultsViewModel
        )
    }

}