package com.example.groupstagesim.components

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.groupstagesim.R
import com.example.groupstagesim.ui.theme.Orange

/**
 * A custom Composable for displaying a rating bar with stars.
 *
 * @param rating The current rating value.
 * @param maxRating The maximum rating value.
 * @param onRatingChanged The callback to be invoked when the user changes the rating.
 */
@Composable
fun RatingBar(
    rating: Int = 1,
    maxRating: Int = 5,
    onRatingChanged: (Int) -> Unit
) {
    Row {
        for (i in 1..maxRating) {
            IconButton(
                // Invoke the provided callback when a star is clicked
                onClick = { onRatingChanged(i) }
            ) {
                // Check if the star should be filled or empty based on the rating
                if (i <= rating) {
                    Icon(
                        tint = Orange,
                        painter = painterResource(id = R.drawable.star_filled_ic),
                        contentDescription = stringResource(R.string.filled_star)
                    )
                } else {
                    Icon(
                        tint = Orange,
                        painter = painterResource(id = R.drawable.star_border_ic),
                        contentDescription = stringResource(R.string.empty_star)
                    )
                }
            }
        }
    }
}