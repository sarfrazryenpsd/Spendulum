package com.ryen.spendulum.models

import com.ryen.spendulum.mock.mockExpenses

data class ExpenseState(
    val recurrence: Recurrence = Recurrence.Weekly,
    val sumTotal: Double = 0.0,
    val expenses: List<Expense> = mockExpenses,
    val isOpen: Boolean = false,
)
