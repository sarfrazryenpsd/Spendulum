package com.ryen.spendulum.models

import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.data.entity.ExpenseModel
import com.ryen.spendulum.mock.mockExpenses

data class ExpenseState(
    val recurrence: Recurrence = Recurrence.Weekly,
    val sumTotal: Double = 0.0,
    val expenses: List<ExpenseModel> = listOf(),
    val isOpen: Boolean = false,
)
