package com.ryen.spendulum.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.ui.theme.Destructive
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TextPrimary
import com.ryen.spendulum.ui.theme.Typography

@Composable
fun TableRow(
    label: String,
    hasArrow: Boolean = false,
    isDestructive: Boolean = false,
    onClick: (String) -> Unit
) {

    val textColor = if(isDestructive) Destructive else TextPrimary

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(label) }
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(
            text = label,
            style = Typography.bodyMedium,
            color = textColor,
        )
        if(hasArrow) {
            Icon(imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null, tint = Color.White.copy(alpha = 0.3f))
        }
    }
}

@Preview
@Composable
private fun TableRow1() {
    SpendulumTheme {
        TableRow(label = "Categories", hasArrow = true, onClick = {})
    }
}
@Preview
@Composable
private fun TableRow2() {
    SpendulumTheme {
        TableRow(label = "Erase all data", isDestructive = true, hasArrow = true, onClick = {})
    }
}