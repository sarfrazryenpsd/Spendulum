package com.ryen.spendulum.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.data.entity.DayExpenses
import com.ryen.spendulum.ui.theme.LabelSecondary
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.utils.formatDay
import com.ryen.spendulum.utils.formatToTwoDecimalPlaces
import java.time.LocalDate

@Composable
fun ExpensesDayGroup(date: LocalDate, dayExpenses: DayExpenses){
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Row (modifier = Modifier.height(32.dp)){
            Text(date.formatDay(), color = LabelSecondary, style = Typography.headlineSmall)
        }
        HorizontalDivider(thickness = 1.dp, color = LabelSecondary.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 4.dp))

        Column {
            dayExpenses.expenses.forEach{ expense ->
                ExpenseRow(expense)
            }
        }

        HorizontalDivider(thickness = 1.dp,color = LabelSecondary.copy(alpha = 0.5f), modifier = Modifier.padding(vertical = 4.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 24.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Text("Total:", style = Typography.bodyMedium, color = LabelSecondary)
            Text("INR ${dayExpenses.total.formatToTwoDecimalPlaces()}", style = Typography.headlineSmall, color = LabelSecondary)
        }
        Spacer(modifier = Modifier.height(15.dp))

    }
}