package com.example.groupstagesim.ui.screens.teams

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.R
import com.example.groupstagesim.components.BlurredBackgroundScreen
import com.example.groupstagesim.components.CustomButton
import com.example.groupstagesim.components.TitleText
import com.example.groupstagesim.ui.theme.PADDING_SMALL
import com.example.groupstagesim.ui.viewmodels.TeamsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Composable for the screen to create teams.
 *
 * @param teamsViewModel The view model for managing teams.
 * @param navigateToSimulateScreen Callback to navigate to the simulation screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTeamsScreen(
    teamsViewModel: TeamsViewModel,
    navigateToSimulateScreen: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }


    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        containerColor = Color.Transparent
    ) { contentPadding ->
        CreateTeams(
            teamsViewModel = teamsViewModel,
            contentPadding = contentPadding,
            scope = scope,
            snackbarHostState = snackbarHostState,
            navigateToSimulateScreen = navigateToSimulateScreen
        )
    }

}


/**
 * Composable for the content of the create teams screen. It includes input fields for team names
 * and ratings and a button to start the simulation.
 *
 * @param teamsViewModel The view model for managing teams data.
 * @param contentPadding Padding values for content layout.
 * @param scope Coroutine scope for launching actions.
 * @param snackbarHostState The state for displaying snackbar messages.
 * @param navigateToSimulateScreen Callback to navigate to the simulation screen.
 */
@Composable
fun CreateTeams(
    teamsViewModel: TeamsViewModel,
    contentPadding: PaddingValues,
    scope: CoroutineScope,
    snackbarHostState: SnackbarHostState,
    navigateToSimulateScreen: () -> Unit
) {
    BlurredBackgroundScreen(
        backgroundImageResId = R.drawable.background_stadium_image
    ) {
        val teamA = remember { mutableStateOf("FC Barcelona") }
        val ratingA = remember { mutableIntStateOf(5) }
        val teamB = remember { mutableStateOf("Ajax") }
        val ratingB = remember { mutableIntStateOf(4) }
        val teamC = remember { mutableStateOf("Real Madrid") }
        val ratingC = remember { mutableIntStateOf((5)) }
        val teamD = remember { mutableStateOf("Liverpool") }
        val ratingD = remember { mutableIntStateOf(3) }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TitleText(text = stringResource(R.string.create_teams), 40.sp)
                TitleText(text = stringResource(R.string.enter_team_names_and_ratings), 20.sp)
            }

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Column {
                    Row {
                        CreateTeamNames(teamA, ratingA)
                        Spacer(modifier = Modifier.padding(PADDING_SMALL))
                        CreateTeamNames(teamB, ratingB)
                    }
                    Spacer(modifier = Modifier.padding(PADDING_SMALL))
                    Row {
                        CreateTeamNames(teamC, ratingC)
                        Spacer(modifier = Modifier.padding(PADDING_SMALL))
                        CreateTeamNames(teamD, ratingD)
                    }
                }

                Spacer(modifier = Modifier.padding(PADDING_SMALL))

                CustomButton(stringResource(R.string.start_sim), width = .9f, onClick = {
                    val inputCheck = teamsViewModel.checkInputs(
                        listOf(
                            teamA.value.lowercase(),
                            teamB.value.lowercase(),
                            teamC.value.lowercase(),
                            teamD.value.lowercase(),
                        )
                    )
                    if (inputCheck != null) {
                        scope.launch {
                            snackbarHostState.showSnackbar(inputCheck)
                        }
                    } else {
                        val teamWithRatings = listOf(
                            teamA.value to ratingA.intValue,
                            teamB.value to ratingB.intValue,
                            teamC.value to ratingC.intValue,
                            teamD.value to ratingD.intValue
                        )
                        Log.i("CreateTeamsScreen", teamWithRatings.toString())
                        teamsViewModel.enterTeams(teamWithRatings)
                        navigateToSimulateScreen()
                    }
                })
            }
        }
    }
}