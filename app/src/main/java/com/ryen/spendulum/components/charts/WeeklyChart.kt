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
import com.ryen.spendulum.models.groupedByDayOfWeek
import com.ryen.spendulum.models.numFormatter
import com.ryen.spendulum.ui.theme.LabelSecondary
import com.ryen.spendulum.ui.theme.Typography
import java.time.DayOfWeek

@Composable
fun WeeklyChart(expenses: List<Expense>) {
    val groupedExpenses = expenses.groupedByDayOfWeek()
    
    BarChart(
        barChartData = BarChartData(
            bars = listOf(
                BarChartData.Bar(
                    label = DayOfWeek.MONDAY.name.substring(0,1),
                    value = groupedExpenses[DayOfWeek.MONDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.TUESDAY.name.substring(0,1),
                    value = groupedExpenses[DayOfWeek.TUESDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.WEDNESDAY.name.substring(0,1),
                    value = groupedExpenses[DayOfWeek.WEDNESDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.THURSDAY.name.substring(0,1),
                    value = groupedExpenses[DayOfWeek.THURSDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.FRIDAY.name.substring(0,1),
                    value = groupedExpenses[DayOfWeek.FRIDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.SATURDAY.name.substring(0,1),
                    value = groupedExpenses[DayOfWeek.SATURDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                ),
                BarChartData.Bar(
                    label = DayOfWeek.SUNDAY.name.substring(0,1),
                    value = groupedExpenses[DayOfWeek.SUNDAY.name]?.total?.toFloat() ?: 0f,
                    color = Color.White
                )
            ),
            padBy = 8f
        ),
        modifier = Modifier.height(147.dp).fillMaxWidth().padding(vertical = 16.dp),
        labelDrawer = LabelDrawer(recurrence = Recurrence.Weekly),
        yAxisDrawer = SimpleYAxisDrawer(
            labelTextColor = LabelSecondary,
            labelValueFormatter = { value -> value.numFormatter() },
            labelRatio = 3,
            labelTextSize = Typography.bodySmall.fontSize
        ),
        barDrawer = BarDrawer(recurrence = Recurrence.Weekly)
    )
}