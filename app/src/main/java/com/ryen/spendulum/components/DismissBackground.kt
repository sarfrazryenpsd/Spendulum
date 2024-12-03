package com.ryen.spendulum.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.ui.theme.Destructive

@Composable
fun DismissBackground(dismissState: SwipeToDismissBoxState) {
    Row (
        modifier = Modifier
            .fillMaxSize()
            .background(Destructive)
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ){
        Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
    }
}