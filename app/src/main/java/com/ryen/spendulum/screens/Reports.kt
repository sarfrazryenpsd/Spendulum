@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.components.ExpensesList
import com.ryen.spendulum.components.charts.MonthlyChart
import com.ryen.spendulum.components.charts.WeeklyChart
import com.ryen.spendulum.components.charts.YearlyChart
import com.ryen.spendulum.mock.mockExpenses
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography
import java.time.LocalDate

@Composable
fun Reports() {
    Scaffold (
        topBar = {
            MediumTopAppBar(title = { Text(text = "Reports", style = Typography.titleLarge) }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground,
                titleContentColor = Color.White
            ))
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding).fillMaxSize().padding(start = 16.dp, end = 16.dp, top = 16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(text = "12 Sep - 18 Sep", style = Typography.titleSmall)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text("INR ", color = Color.White.copy(alpha = 0.6f), style = Typography.bodySmall)
                            Text("85,000", style = Typography.headlineSmall)
                        }

                    }
                    Column {
                        Text("Avg/Day", style = Typography.titleSmall)
                        Spacer(modifier = Modifier.height(8.dp))
                        Row {
                            Text("INR ", color = Color.White.copy(alpha = 0.6f), style = Typography.bodySmall)
                            Text("85,000", style = Typography.headlineSmall)
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                YearlyChart(expenses = mockExpenses)
                Spacer(modifier = Modifier.height(16.dp))
                ExpensesList(expenses = mockExpenses)
            }
        }
    )
}