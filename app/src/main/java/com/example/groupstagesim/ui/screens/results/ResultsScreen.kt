package com.example.groupstagesim.ui.screens.results

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.R
import com.example.groupstagesim.components.BlurredBackgroundScreen
import com.example.groupstagesim.components.CustomButton
import com.example.groupstagesim.components.TitleText
import com.example.groupstagesim.ui.theme.PADDING_SMALL
import com.example.groupstagesim.ui.viewmodels.ResultsViewModel
import com.example.groupstagesim.util.Constants.AMOUNT_OF_ROUNDS

/**
 * Composable for displaying the results screen.
 *
 * @param navigateToStartScreen Callback to navigate to the start screen.
 * @param resultsViewModel ViewModel containing results data.
 */
@Composable
fun ResultsScreen(
    navigateToStartScreen: () -> Unit,
    resultsViewModel: ResultsViewModel
) {

    val allMatches = resultsViewModel.allMatches.collectAsState()
    val allTeams = resultsViewModel.allTeams.collectAsState()
    val sortedTeams = resultsViewModel.allTeamsSorted.collectAsState()

    BlurredBackgroundScreen(
        backgroundImageResId = R.drawable.background_stadium_image
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleText(text = stringResource(R.string.results), textSize = 40.sp)
            if (allMatches.value.isNotEmpty() && allTeams.value.isNotEmpty()) {
                resultsViewModel.sortTeamList()
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    item {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            LazyRow(
                                horizontalArrangement = Arrangement.Center
                            ) {
                                items(AMOUNT_OF_ROUNDS) { index ->
                                    val filteredMatches =
                                        allMatches.value.filter { it.roundNumber == index + 1 }
                                    ResultsRoundCard(
                                        matches = filteredMatches,
                                        allTeams = allTeams.value
                                    )
                                }
                            }
                        }
                    }
                    if (sortedTeams.value.isNotEmpty()) {
                        item {
                            ResultsStandingCard(allTeams = sortedTeams.value)
                        }
                    }

                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = PADDING_SMALL),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            CustomButton(text = stringResource(R.string.main_menu), width = .7f) {
                                navigateToStartScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}