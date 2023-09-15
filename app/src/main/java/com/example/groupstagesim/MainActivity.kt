package com.example.groupstagesim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.groupstagesim.components.BlurredBackgroundScreen
import com.example.groupstagesim.navigation.SetupNavigation
import com.example.groupstagesim.ui.theme.GroupstageSimTheme
import com.example.groupstagesim.ui.viewmodels.ResultsViewModel
import com.example.groupstagesim.ui.viewmodels.SimulateViewModel
import com.example.groupstagesim.ui.viewmodels.TeamsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val teamsViewModel: TeamsViewModel by viewModels()
    private val simulateViewModel: SimulateViewModel by viewModels()
    private val resultsViewModel: ResultsViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            GroupstageSimTheme {
                navController = rememberNavController()
                BlurredBackgroundScreen(backgroundImageResId = R.drawable.background_stadium_image) {
                    // Initialize navigation using the SetupNavigation composable.
                    SetupNavigation(
                        navController = navController,
                        teamsViewModel = teamsViewModel,
                        resultsViewModel = resultsViewModel,
                        simulateViewModel = simulateViewModel
                    )
                }
            }
        }
    }
}