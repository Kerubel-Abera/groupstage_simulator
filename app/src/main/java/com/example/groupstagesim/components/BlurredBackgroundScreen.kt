package com.example.groupstagesim.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.groupstagesim.R
import com.example.groupstagesim.ui.theme.Blue
import com.example.groupstagesim.ui.theme.PADDING_SMALL

/**
 * Composable for displaying a screen with a blurred background image and content.
 *
 * @param backgroundImageResId The resource ID of the background image.
 * @param content The content to display on top of the background image.
 */
@Composable
fun BlurredBackgroundScreen(
    backgroundImageResId: Int,
    content: @Composable () -> Unit
) {
    // Create a Box composable to layer the background, overlay, and content
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        // Display the blurred background image
        Image(
            modifier = Modifier
                .fillMaxSize()
                .blur(radius = 15.dp),
            painter = painterResource(id = backgroundImageResId),
            contentScale = ContentScale.Crop,
            contentDescription = stringResource(R.string.background_image)
        )

        // Overlay a semi-transparent blue background
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Blue.copy(alpha = 0.8f))

        )

        // Display the content within a Column
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(PADDING_SMALL)
        ) {
            content()
        }
    }
}