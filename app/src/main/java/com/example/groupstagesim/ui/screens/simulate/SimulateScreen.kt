package com.example.groupstagesim.ui.screens.simulate

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.R
import com.example.groupstagesim.components.AnimatedText
import com.example.groupstagesim.components.BlurredBackgroundScreen
import com.example.groupstagesim.components.TitleText
import com.example.groupstagesim.ui.viewmodels.SimulateViewModel
import kotlinx.coroutines.delay

/**
 * Composable for simulating matches screen.
 *
 * @param navigateToResultsScreen Callback to navigate to the results screen.
 * @param simulateViewModel ViewModel for simulating matches.
 */
@Composable
fun SimulateScreen(
    navigateToResultsScreen: () -> Unit,
    simulateViewModel: SimulateViewModel
) {
    val teams = simulateViewModel.allTeams.collectAsState()

    // State to control showing the match message.
    var showMessage by remember { mutableStateOf(false) }

    BlurredBackgroundScreen(
        backgroundImageResId = R.drawable.background_stadium_image
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {

            // Execute simulation and show match messages.
            LaunchedEffect(key1 = Unit) {
                simulateViewModel.simulateMatches(teams.value)

                for (i in 1..4) {
                    delay(1500)
                    showMessage = true
                    delay(1500)
                    showMessage = false
                }

                // Navigate to the results screen when simulation is complete.
                navigateToResultsScreen()
            }

            // Show animated match messages when [showMessage] is true.
            if (showMessage) {
                val message = simulateViewModel.generateMatchMessage()
                AnimatedText(
                    text = message,
                    modifier = Modifier
                )
            }

            // Display "MATCHES IN PROGRESS..." text animation.
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                SimulatingTextAnimation()
            }
        }


    }
}

/**
 * Composable for MATCHES IN PROGRESS.. text animation.
 */
@Composable
fun SimulatingTextAnimation() {
    var dots by remember { mutableStateOf(".") }

    LaunchedEffect(dots) {
        while (true) {
            delay(700)
            dots = when (dots) {
                "..." -> "."
                "." -> ".."
                ".." -> "..."
                else -> "..."
            }
        }
    }

    TitleText(text = stringResource(R.string.matches_in_progress) + dots, textSize = 40.sp)

}