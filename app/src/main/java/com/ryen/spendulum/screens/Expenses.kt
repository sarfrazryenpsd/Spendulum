@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography

@Composable
fun Expenses(navController: NavController) {
    Scaffold (
        topBar = {
            MediumTopAppBar(title = { Text("Expenses",style = Typography.titleLarge) }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground,
                titleContentColor = Color.White
            ))
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Text(text = "Hello Expenses!")
            }
        }
    )
}