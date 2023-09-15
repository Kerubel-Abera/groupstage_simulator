package com.example.groupstagesim.navigation

import androidx.navigation.NavController
import com.example.groupstagesim.util.Constants.CREATE_TEAMS_SCREEN
import com.example.groupstagesim.util.Constants.RESULTS_SCREEN
import com.example.groupstagesim.util.Constants.SIMULATE_MATCH_SCREEN
import com.example.groupstagesim.util.Constants.START_SCREEN

class Screens(navController: NavController) {


    val start: () -> Unit = {
        navController.navigate(START_SCREEN) {
            popUpTo(START_SCREEN) { inclusive = true }
        }
    }

    val createTeams: () -> Unit = {
        navController.navigate(CREATE_TEAMS_SCREEN)
    }

    val simulateMatch: () -> Unit = {
        navController.navigate(SIMULATE_MATCH_SCREEN) {
            popUpTo(SIMULATE_MATCH_SCREEN) { inclusive = true }
        }
    }

    val results: () -> Unit = {
        navController.navigate(RESULTS_SCREEN) {
            popUpTo(RESULTS_SCREEN) { inclusive = true }
        }
    }

}