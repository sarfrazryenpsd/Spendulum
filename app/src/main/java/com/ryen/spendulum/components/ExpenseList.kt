package com.ryen.spendulum.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.data.entity.ExpenseModel
import com.ryen.spendulum.utils.groupedByDay

@Composable
fun ExpensesList(expenses: List<ExpenseModel>) {
    val groupedExpenses = expenses.groupedByDay()

    LazyColumn {
        if (groupedExpenses.isEmpty()) {
            item{ Text("No data for selected date range.", modifier = Modifier.padding(top = 32.dp)) }
        } else {
            groupedExpenses.forEach { (date, dayExpenses) ->
                item(key = date) {
                    ExpensesDayGroup(date, dayExpenses)
                }
            }
            item { Spacer(modifier = Modifier.height(50.dp)) }
        }
    }
}