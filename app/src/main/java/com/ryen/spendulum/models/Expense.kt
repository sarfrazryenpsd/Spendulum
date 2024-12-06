package com.ryen.spendulum.models

import java.time.LocalDate
import java.time.LocalDateTime

data class Expense(
    val amount: Double,
    val date: LocalDateTime,
    val note: String?,
    val category: Category,
    val recurrence: Recurrence,

)

data class DayExpenses(
    val expenses: List<Expense>,
    val total: Double
)

fun List<Expense>.groupByDay(): Map<LocalDate, DayExpenses> {
    return this.groupBy { it.date.toLocalDate() }
        .mapValues { (_, expenses) ->
            DayExpenses(
                expenses = expenses.sortedByDescending { it.date },
                total = expenses.sumOf { it.amount }
            )
        }.toSortedMap(compareByDescending { it })
}

fun Double.formatToTwoDecimalPlaces(): String {
    return String.format("%.2f", this)
}
