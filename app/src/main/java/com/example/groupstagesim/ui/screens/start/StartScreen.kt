package com.example.groupstagesim.ui.screens.start

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.R
import com.example.groupstagesim.components.BlurredBackgroundScreen
import com.example.groupstagesim.components.CustomButton
import com.example.groupstagesim.components.TitleText
import com.example.groupstagesim.ui.viewmodels.TeamsViewModel

/**
 * Composable for the start screen of the app.
 *
 * @param navigateToCreateTeamsScreen Callback to navigate to the create teams screen.
 * @param teamsViewModel ViewModel for managing teams data.
 */
@Composable
fun StartScreen(
    navigateToCreateTeamsScreen: () -> Unit,
    teamsViewModel: TeamsViewModel
) {
    // Reset teams table
    teamsViewModel.resetTeams()

    BlurredBackgroundScreen(
        backgroundImageResId = R.drawable.background_stadium_image
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .offset(y = (-25).dp),
                    painter = painterResource(id = R.drawable.soccer_ball_image),
                    contentDescription = stringResource(R.string.soccer_ball)
                )
                TitleText(text = stringResource(R.string.groupstage_simulator), 40.sp)
            }
            CustomButton(text = stringResource(R.string.start), width = .5F, onClick = navigateToCreateTeamsScreen)
        }
    }
}