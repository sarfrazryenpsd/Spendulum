package com.ryen.spendulum.utils

import com.ryen.spendulum.models.Recurrence
import java.time.LocalDate
import java.time.YearMonth

data class DateRangeData(
    val dateStart: LocalDate,
    val dateEnd: LocalDate,
    val daysInRange: Int
)

fun calculateDateRange(recurrence: Recurrence, page: Int): DateRangeData {
    val today = LocalDate.now()
    lateinit var start: LocalDate
    lateinit var end: LocalDate
    var daysInRange: Int = 7

    when (recurrence) {
        Recurrence.Daily -> {
            start = today.minusDays((page * 1).toLong())
            end = start
        }

        Recurrence.Weekly -> {
            start = LocalDate.now().minusDays(today.dayOfWeek.value.toLong() -1)
                .minusDays((page * 7).toLong())
            end = start.plusDays(6)
            daysInRange = 7
        }

        Recurrence.Monthly -> {
            start = LocalDate.of(today.year, today.month, 1)
                .minusMonths((page * 1).toLong())
            val daysInMonth = YearMonth.of(start.year, start.month).lengthOfMonth()
            end = start.plusDays(daysInMonth.toLong() - 1)
            daysInRange = daysInMonth

        }

        Recurrence.Yearly -> {
            start = LocalDate.of(today.year, 1, 1)
            end = LocalDate.of(today.year, 12, 31)
            daysInRange = 365
        }

        else -> Unit
    }

    return DateRangeData(
        dateStart = start,
        dateEnd = end,
        daysInRange = daysInRange
    )
}