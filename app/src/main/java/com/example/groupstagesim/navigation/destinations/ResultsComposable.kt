package com.example.groupstagesim.navigation.destinations

import androidx.activity.compose.BackHandler
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.groupstagesim.ui.screens.results.ResultsScreen
import com.example.groupstagesim.ui.viewmodels.ResultsViewModel
import com.example.groupstagesim.util.Constants.RESULTS_SCREEN

fun NavGraphBuilder.resultsComposable(
    navigateToStartScreen: () -> Unit,
    resultsViewModel: ResultsViewModel
) {
    composable(
        route = RESULTS_SCREEN
    ) {
        BackHandler(true) {
            // do nothing
        }
        ResultsScreen(
            navigateToStartScreen = navigateToStartScreen,
            resultsViewModel = resultsViewModel
        )
    }
}