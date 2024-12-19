package com.ryen.spendulum.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ryen.spendulum.ui.theme.Destructive
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.Typography

@Composable
fun AlertDialog2(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: ImageVector,
) {
    AlertDialog(
        icon = {
            Icon(icon, contentDescription = "Example Icon", tint = Destructive, modifier = Modifier.size(48.dp))
        },
        title = {
            Text(text = dialogTitle, style = Typography.titleMedium)
        },
        text = {
            Text(text = dialogText, style = Typography.labelMedium.copy(fontSize = 14.sp))
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            Button(
                onClick = {
                    onConfirmation()
                }, colors = ButtonDefaults.buttonColors(
                    containerColor = Destructive,
                    contentColor = Color.White
                )
            ) {
                Text("Erase data")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text("Dismiss", color = Color.White)
            }
        },
        shape = Shapes.large
    )
}