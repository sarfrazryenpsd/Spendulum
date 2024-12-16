package com.ryen.spendulum.models

import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.data.entity.ExpenseModel
import com.ryen.spendulum.mock.mockExpenses
import java.time.LocalDateTime

data class ReportState(
    val expense: List<ExpenseModel> = emptyList(),
    val dateStart: LocalDateTime = LocalDateTime.now(),
    val dateEnd: LocalDateTime = LocalDateTime.now().minusDays(7),
    val avgPerDay: Double = 0.0,
    val totalInRange: Double = 0.0
)
