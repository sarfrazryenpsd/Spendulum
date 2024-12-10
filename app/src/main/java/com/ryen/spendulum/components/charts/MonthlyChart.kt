package com.ryen.spendulum.components.charts

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.tehras.charts.bar.BarChart
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.yaxis.SimpleYAxisDrawer
import com.ryen.spendulum.models.Expense
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.models.groupedByDayOfMonth
import com.ryen.spendulum.models.numFormatter
import com.ryen.spendulum.ui.theme.LabelSecondary
import com.ryen.spendulum.ui.theme.Typography
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.YearMonth

@Composable
fun MonthlyChart(expenses: List<Expense>, month: LocalDate) {
    val groupedExpenses = expenses.groupedByDayOfMonth()
    val daysInMonth = YearMonth.of(month.year, month.month).lengthOfMonth()

    BarChart(
        barChartData = BarChartData(
            bars = buildList() {
                for (i in 1..daysInMonth){
                    add(
                        BarChartData.Bar(
                            label = i.toString(),
                            value = groupedExpenses[i]?.total?.toFloat() ?: 0f,
                            color = Color.White
                        )
                    )
                }
            },
        ),
        modifier = Modifier.height(147.dp).fillMaxWidth().padding(vertical = 16.dp),
        labelDrawer = LabelDrawer(recurrence = Recurrence.Monthly),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = LabelSecondary,
            labelValueFormatter = { value -> value.numFormatter() },
            labelRatio = 3,
            labelTextSize = Typography.bodySmall.fontSize
        ),
        barDrawer = BarDrawer(recurrence = Recurrence.Monthly)
    )
}