package com.example.groupstagesim.ui.screens.results

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.R
import com.example.groupstagesim.data.models.Team
import com.example.groupstagesim.ui.theme.BlueVariant
import com.example.groupstagesim.ui.theme.CORNERSHAPE_SMALL
import com.example.groupstagesim.ui.theme.Gold
import com.example.groupstagesim.ui.theme.PADDING_MEDIUM
import com.example.groupstagesim.ui.theme.PADDING_SMALL
import com.example.groupstagesim.ui.theme.Silver

/**
 * Composable for displaying the rounds and standings cards.
 *
 * @param allTeams List of teams to display in the standings.
 */
@Composable
fun ResultsStandingCard(
   allTeams: List<Team>
){
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

        val titles = listOf(
            stringResource(R.string.pos) to 0.1f,
            stringResource(R.string.team) to 0.3f,
            stringResource(R.string.played) to 0.1f,
            stringResource(R.string.win) to 0.1f,
            stringResource(R.string.draw) to 0.1f,
            stringResource(R.string.loss) to 0.1f,
            stringResource(R.string.for_title) to 0.1f,
            stringResource(R.string.against) to 0.1f,
            stringResource(R.string.difference) to 0.1f,
            stringResource(R.string.points) to 0.1f
        )
        Column(
            modifier = Modifier.padding(PADDING_SMALL)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                titles.forEachIndexed { index, title ->
                    Card(
                        modifier = Modifier
                            .weight(title.second)
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
                                text = title.first,
                                fontSize = 14.sp,
                                color = Color.White,
                                fontWeight = FontWeight.SemiBold
                            )
                        }
                    }
                    // Add spacing between columns.
                    if (index < titles.size - 1) {
                        Spacer(modifier = Modifier.width(PADDING_SMALL))
                    }
                }
            }

            for((pos, team) in allTeams.withIndex()) {

                val backgroundColor = if(pos == 0) Gold else if (pos == 1) Silver else Color.Transparent

                Box(
                    modifier = Modifier.background(
                        color = backgroundColor,
                        shape = RoundedCornerShape(CORNERSHAPE_SMALL)
                    )
                ) {

                    Row(
                        modifier = Modifier.padding(top = PADDING_MEDIUM, bottom = PADDING_MEDIUM)
                    ) {
                        LeaderboardText(
                            text = "${pos + 1}.",
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.teamName,
                            modifier = Modifier
                                .weight(.3F)
                        )
                        LeaderboardText(
                            text = team.played.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.win.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.draw.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.loss.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.goalsFor.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.goalsAgainst.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.difference.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                        LeaderboardText(
                            text = team.points.toString(),
                            modifier = Modifier
                                .weight(.1F)
                        )
                    }
                }

                Divider(
                    modifier = Modifier.padding(vertical = PADDING_SMALL)
                )
            }

        }
    }
}


@Composable
fun LeaderboardText(text: String, modifier: Modifier){
    Text(
        modifier = modifier,
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        color = Color.Black,
        textAlign = TextAlign.Center,
    )
}

@Preview(
    showBackground = true,
    backgroundColor = 3943,
    device = "spec:width=390dp,height=844dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun ResultsStandingCardPreview(){

    val mockTeams = listOf(
        Team(0, "test", 1, 1, 1, 1, 1, 1, 1, -1, 1),
        Team(0, "test", 1, 1, 1, 1, 1, 1, 1, 1, 1),
        Team(0, "test", 1, 1, 1, 1, 25, 1, 1, 1, 1),
        Team(0, "test", 1, 1, 1, 1, 1, 1, 1, 1, 1),
    )

    ResultsStandingCard(mockTeams)
}