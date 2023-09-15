package com.example.groupstagesim.ui.screens.results

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.R
import com.example.groupstagesim.data.models.Match
import com.example.groupstagesim.data.models.Team
import com.example.groupstagesim.ui.theme.BlueVariant
import com.example.groupstagesim.ui.theme.CORNERSHAPE_SMALL
import com.example.groupstagesim.ui.theme.PADDING_MEDIUM
import com.example.groupstagesim.ui.theme.PADDING_SMALL
import com.example.groupstagesim.util.Constants.MATCHES_PER_ROUND

/**
 * Composable for displaying a round of matches in the results screen.
 *
 * @param matches List of matches in the round.
 * @param allTeams List of all teams.
 */
@Composable
fun ResultsRoundCard(matches: List<Match>, allTeams: List<Team>) {
    ElevatedCard(
        modifier = Modifier
            .widthIn(min = 150.dp)
            .heightIn(min = 100.dp)
            .padding(all = PADDING_MEDIUM),
        shape = RoundedCornerShape(CORNERSHAPE_SMALL),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .padding(PADDING_SMALL)
                .width(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(
                modifier = Modifier
                    .padding(bottom = PADDING_MEDIUM),
                colors = CardDefaults.cardColors(
                    containerColor = BlueVariant
                ),
                shape = RoundedCornerShape(CORNERSHAPE_SMALL)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(PADDING_SMALL),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.round) + matches[0].roundNumber,
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            val titles = listOf(
                stringResource(R.string.home),
                stringResource(R.string.score),
                stringResource(R.string.away)
            )

            Row {
                // Create three title rows for the "Home", "Score", "Away" titles
                for (i in 0..2) {
                    Card(
                        modifier = Modifier
                            .weight(.33f),
                        colors = CardDefaults.cardColors(
                            containerColor = BlueVariant
                        ),
                        shape = RoundedCornerShape(CORNERSHAPE_SMALL)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(PADDING_SMALL),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = titles[i],
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    if (i < 2) {
                        Spacer(modifier = Modifier.width(PADDING_SMALL))
                    }
                }
            }

            val textToShowcase = listOf(
                allTeams[matches[0].homeTeamId - 1].teamName,
                "${matches[0].homeTeamScore} - ${matches[0].awayTeamScore}",
                allTeams[matches[0].awayTeamId - 1].teamName,
                allTeams[matches[1].homeTeamId - 1].teamName,
                "${matches[1].homeTeamScore} - ${matches[1].awayTeamScore}",
                allTeams[matches[1].awayTeamId - 1].teamName
            )

            // Create two rows of "Team X  X-X  Team X" text
            for (i in 0..MATCHES_PER_ROUND) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    when (i) {
                        0 -> {
                            //selects which element in textToShowcase to use
                            for (j in 0..2) {
                                Text(
                                    modifier = Modifier
                                        .weight(.33f)
                                        .padding(PADDING_SMALL),
                                    text = textToShowcase[j],
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = true,
                                    maxLines = 1
                                )
                            }
                        }

                        1 -> {
                            //selects which element in textToShowcase to use
                            for (j in 3..5) {
                                Text(
                                    modifier = Modifier
                                        .weight(.33f)
                                        .padding(PADDING_SMALL),
                                    text = textToShowcase[j],
                                    textAlign = TextAlign.Center,
                                    fontSize = 12.sp,
                                    overflow = TextOverflow.Ellipsis,
                                    softWrap = true,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}