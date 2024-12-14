@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.R
import com.ryen.spendulum.components.ExpensesList
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.ui.theme.BackGroundElevate
import com.ryen.spendulum.ui.theme.LabelSecondary
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.utils.formatToTwoDecimalPlaces
import com.ryen.spendulum.viewModels.ExpenseViewModel

@Composable
fun Expenses(expenseViewModel: ExpenseViewModel = ExpenseViewModel()) {
    val state by expenseViewModel.uiState.collectAsState()
    var expanded by remember { mutableStateOf(false) }
    val recurrences = listOf(
        Recurrence.Daily,
        Recurrence.Weekly,
        Recurrence.Monthly,
        Recurrence.Yearly
    )
    Scaffold (
        topBar = {
            MediumTopAppBar(title = { Text("Expenses",style = Typography.titleLarge) }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground,
                titleContentColor = Color.White
            ))
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Total for:",
                        style = Typography.bodyMedium,
                        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp, end = 16.dp)
                    )
                    Row(
                        modifier = Modifier
                            .size(width = 155.dp, height = 34.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(BackGroundElevate)
                            .clickable { expanded = !expanded },
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                        )
                    {
                        Text(
                            text = state.recurrence.target,
                            style = Typography.titleMedium,
                            modifier = Modifier.padding(end = 2.dp)
                        )
                        Icon(
                            painter = painterResource(R.drawable.unfold),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = LabelSecondary
                        )

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
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 34.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Icon(
                        painter = painterResource(R.drawable.rupee),
                        contentDescription = null,
                        modifier = Modifier.size(41.dp),
                        tint = Color.White.copy(alpha = 0.6f)
                    )
                    Text(
                        text = state.sumTotal.formatToTwoDecimalPlaces(),
                        style = Typography.titleLarge.copy(fontWeight = FontWeight.Normal),
                        minLines = 1
                    )
                }
                ExpensesList(expenses = state.expenses)
            }
        }
    )
}


@Preview
@Composable
private fun ExpensesPrev() {
    SpendulumTheme {
        Expenses()
    }
}