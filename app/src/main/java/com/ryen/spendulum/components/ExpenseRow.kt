package com.ryen.spendulum.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.models.Expense
import com.ryen.spendulum.models.formatToTwoDecimalPlaces
import com.ryen.spendulum.ui.theme.LabelSecondary
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.utils.formatTime

@Composable
fun ExpenseRow(expense: Expense) {
    Column (modifier = Modifier.fillMaxWidth()){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(text = expense.note?:expense.category.name, style = Typography.headlineSmall)
            Text(text = "INR "+expense.amount.formatToTwoDecimalPlaces(), style = Typography.headlineSmall)
        }
        Row (
            modifier = Modifier.fillMaxWidth().padding(top = 6.dp, bottom = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Box(
                modifier = Modifier
                    .clip(Shapes.medium)
                    .background(expense.category.color.copy(alpha = 0.25f))
            ){
                Text(
                    text = expense.category.name,
                    style = Typography.labelMedium,
                    color = expense.category.color,
                    modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
                )
            }
            Text(text = expense.date.formatTime(), color = LabelSecondary, style = Typography.bodyMedium)
        }
    }
}

