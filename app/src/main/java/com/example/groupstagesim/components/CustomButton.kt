package com.example.groupstagesim.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.groupstagesim.ui.theme.CORNERSHAPE_LARGE
import com.example.groupstagesim.ui.theme.Orange
import com.example.groupstagesim.ui.theme.PADDING_MEDIUM

/**
 * A custom Composable button with customizable text, width, and click action.
 *
 * @param text The text to display on the button.
 * @param width The width of the button as a fraction of the available width.
 * @param onClick The action to perform when the button is clicked.
 */
@Composable
fun CustomButton(
    text: String,
    width: Float,
    onClick: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(width),
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = Orange,
            contentColor = Color.White
        ),
        shape = RoundedCornerShape(CORNERSHAPE_LARGE),
        contentPadding = PaddingValues(vertical = PADDING_MEDIUM)
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 36.sp
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 3943,
    device = "spec:width=390dp,height=844dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
@Composable
fun CustomButtonPreview() {
    // Example usage of the CustomButton with a preview
    CustomButton(
        text = "Start",
        width = 0.8f
    ) {
        //Do nothing
    }
}