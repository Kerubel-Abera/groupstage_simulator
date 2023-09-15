package com.example.groupstagesim.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable for displaying animated text that moves and fades in.
 *
 * @param text The text to display.
 * @param modifier The modifier for the AnimatedText.
 */
@Composable
fun AnimatedText(text: String, modifier: Modifier) {
    // Get the screen dimensions from the current configuration
    val configuration = LocalConfiguration.current
    val screenWidthInDp = configuration.screenWidthDp
    val screenHeightInDp = configuration.screenHeightDp

    // Create an Animatable for controlling the animation
    val animation = remember { Animatable(0f) }

    // Generate random initial x and y positions within half the screen dimensions
    val x by remember { mutableStateOf((0..(screenWidthInDp * 0.5).toInt()).random().toFloat().dp) }
    val y by remember {
        mutableStateOf(
            (0..(screenHeightInDp * 0.5).toInt()).random().toFloat().dp
        )
    }


    // Launch the animation effect
    LaunchedEffect(Unit) {
        animation.animateTo(1f, animationSpec = tween(durationMillis = 3000))
    }

    // Calculate the offset and alpha values based on the animation progress
    val offsetY by animateFloatAsState(
        targetValue = if (animation.value < 0.5f) 0f else -20f,
        animationSpec = tween(durationMillis = 3000)
    )

    val alpha by animateFloatAsState(
        targetValue = if (animation.value < 0.5f) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )

    // Display the text with the specified animation
    Box(
        modifier = modifier
            .offset(
                x = x,
                y = y
            )
            .alpha(alpha)
    ) {
        Box(
            modifier = modifier
                .offset(y = offsetY.dp)
                .alpha(alpha)
        ) {
            TitleText(text = text, textSize = 15.sp)
        }
    }
}