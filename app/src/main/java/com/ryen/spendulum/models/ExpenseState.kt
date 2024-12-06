package com.ryen.spendulum.models

data class ExpenseState(
    val recurrence: Recurrence = Recurrence.Weekly,
    val sumTotal: Double = 1250.98
)
