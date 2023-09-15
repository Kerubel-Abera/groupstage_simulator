package com.example.groupstagesim.navigation.destinations

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.groupstagesim.ui.screens.simulate.SimulateScreen
import com.example.groupstagesim.ui.viewmodels.SimulateViewModel
import com.example.groupstagesim.util.Constants.SIMULATE_MATCH_SCREEN

fun NavGraphBuilder.simulateComposable(
    navigateToResultsScreen: () -> Unit,
    simulateViewModel: SimulateViewModel
) {
    composable(
        route = SIMULATE_MATCH_SCREEN
    ) {
        BackHandler(true) {
            // do nothing
        }
        SimulateScreen(
            navigateToResultsScreen = navigateToResultsScreen,
            simulateViewModel = simulateViewModel
        )
    }
}