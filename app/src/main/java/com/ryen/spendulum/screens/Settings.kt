@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ryen.spendulum.components.TableRow
import com.ryen.spendulum.ui.theme.BackGroundElevate
import com.ryen.spendulum.ui.theme.Divider
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.TopAppBarBackground

@Composable
fun Settings(navController: NavController) {
    Scaffold (
        topBar = {
            MediumTopAppBar(title = { Text("Settings") }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground,
                titleContentColor = Color.White
            ))
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clip(Shapes.large)
                    .background(BackGroundElevate)
                ) {
                    TableRow(
                        label = "Categories",
                        hasArrow = true,
                        onClick = {navController.navigate("settings/categories")}
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Erase Data",
                        isDestructive = true,
                        onClick = {}
                    )
                }
            }
        }
    )
}