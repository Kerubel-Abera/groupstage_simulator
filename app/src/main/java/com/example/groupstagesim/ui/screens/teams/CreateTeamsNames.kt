package com.example.groupstagesim.ui.screens.teams

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.R
import com.example.groupstagesim.components.RatingBar
import com.example.groupstagesim.ui.theme.BlueDark
import com.example.groupstagesim.ui.theme.BlueDarker
import com.example.groupstagesim.ui.theme.CORNERSHAPE_LARGE
import com.example.groupstagesim.ui.theme.CORNERSHAPE_SMALL
import com.example.groupstagesim.ui.theme.PADDING_SMALL
import com.example.groupstagesim.util.Constants.MAX_TEAM_NAME_CHARACTERS

/**
 * Composable for creating a team name input field along with a rating bar.
 *
 * @param text MutableState holding the team name input text.
 * @param rating MutableState holding the team's rating.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTeamNames(
    text: MutableState<String>,
    rating: MutableState<Int>
) {
    Column(
        Modifier
            .background(
                BlueDark,
                RoundedCornerShape(CORNERSHAPE_LARGE)
            )
            .padding(PADDING_SMALL)
            .height(100.dp)
            .width(250.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .height(50.dp)
                .background(
                    color = BlueDarker,
                    shape = RoundedCornerShape(CORNERSHAPE_SMALL)
                ),
            value = text.value,
            onValueChange = {
                if (it.length <= MAX_TEAM_NAME_CHARACTERS) {
                    text.value = it
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                placeholderColor = Color.LightGray,
                textColor = Color.White
            ),
            placeholder = {
                Text(
                    text = stringResource(R.string.team_name),
                    fontSize = 14.sp
                )
            },
            singleLine = true
        )

        RatingBar(
            rating = rating.value,
            onRatingChanged = { newRating ->
                rating.value = newRating
            }
        )

    }
}