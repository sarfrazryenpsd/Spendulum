@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.components.ReportsPage
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.ui.theme.Primary
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.viewModels.ExpenseViewModel

@Composable
fun Reports(expenseViewModel: ExpenseViewModel = ExpenseViewModel()) {
    val state by expenseViewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val recurrences = listOf(
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly
    )
    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Reports", style = Typography.titleLarge) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground,
                    titleContentColor = Color.White
                ),
                actions = {
                    TextButton(onClick = {expanded = !expanded}) {
                        Icon(imageVector = Icons.Default.DateRange, contentDescription = null, tint = Primary)

                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            } ,// Close menu when clicked outside
                            modifier = Modifier.background(color = TopAppBarBackground)
                        ) {
                            // Dropdown items", "Option 3") // Dropdown items
                            recurrences.forEach { recurrence ->
                                DropdownMenuItem(
                                    text = {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Text(
                                                text = recurrence.name,
                                                modifier = Modifier.padding(start = 8.dp),
                                                style = Typography.bodyMedium
                                            )
                                        }
                                    },
                                    onClick = {
                                        expenseViewModel.setRecurrence(recurrence) // Update selected item
                                        expanded = false // Close the menu
                                    },
                                    contentPadding = PaddingValues(horizontal = 8.dp)
                                )
                            }
                        }
                    }
                }
            )
        },
        content = { innerPadding ->
            val numOfPages = when(state.recurrence){
                Recurrence.Weekly -> 53
                Recurrence.Monthly -> 12
                Recurrence.Yearly -> 1
                else -> 53
            }
            val pagerState = rememberPagerState (pageCount = {numOfPages})
            HorizontalPager(state = pagerState, reverseLayout = true, modifier = Modifier.padding(innerPadding)){ page ->
                ReportsPage(page = page, recurrence = state.recurrence )
            }
        }
    )
}