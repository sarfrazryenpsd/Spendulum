package com.ryen.spendulum.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ryen.spendulum.components.charts.MonthlyChart
import com.ryen.spendulum.components.charts.WeeklyChart
import com.ryen.spendulum.components.charts.YearlyChart
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.models.numFormatter
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.viewModels.ReportViewModel
import com.ryen.spendulum.viewModels.viewModelFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ReportsPage(
    recurrence: Recurrence,
    page: Int,
    vm: ReportViewModel = viewModel(
        key = "$page-${recurrence.name}", factory = viewModelFactory {
        ReportViewModel(page, recurrence)
    })
) {
    val state by vm.uiState.collectAsState()
    val formatter = DateTimeFormatter.ofPattern("dd MMM")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp, top = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {

                Text(text = "${state.dateStart.format(formatter)} - ${state.dateEnd.format(formatter)}", style = Typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        "INR ",
                        color = Color.White.copy(alpha = 0.6f),
                        style = Typography.bodySmall
                    )
                    Text(state.totalInRange.toFloat().numFormatter(), style = Typography.headlineSmall)
                }

            }
            Column {
                Text("Avg/Day", style = Typography.titleSmall)
                Spacer(modifier = Modifier.height(8.dp))
                Row {
                    Text(
                        "INR ",
                        color = Color.White.copy(alpha = 0.6f),
                        style = Typography.bodySmall
                    )
                    Text(state.avgPerDay.toFloat().numFormatter(), style = Typography.headlineSmall)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (recurrence) {
            Recurrence.Weekly -> WeeklyChart(expenses = state.expense)
            Recurrence.Monthly -> MonthlyChart(expenses = state.expense, LocalDate.now())
            Recurrence.Yearly -> YearlyChart(expenses = state.expense)
            else -> {}
        }
        Spacer(modifier = Modifier.height(16.dp))
        ExpensesList(expenses = state.expense)
    }
}