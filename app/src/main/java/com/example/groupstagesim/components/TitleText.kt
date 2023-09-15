package com.example.groupstagesim.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * A custom Composable for displaying a title text with a shadow effect.
 *
 * @param text The text to be displayed.
 * @param textSize The size of the text.
 */
@Composable
fun TitleText(text: String, textSize: TextUnit) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        // Shadowed text with reduced alpha and offset
        Text(
            modifier = Modifier
                .alpha(0.25f)
                .offset(x = 1.dp, y = 3.dp),
            text = text,
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.Black
        )
        // Main text with full alpha and no offset, creating the title effect
        Text(
            text = text,
            fontSize = textSize,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = Color.White
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    backgroundColor = 3943,
    device = "spec:width=390dp,height=844dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
fun TitleTextPreview() {
    TitleText(text = "GROUPSTAGE SIMULATOR", 40.sp)
}