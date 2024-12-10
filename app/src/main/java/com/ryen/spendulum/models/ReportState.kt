package com.ryen.spendulum.models

import java.time.LocalDate

data class ReportState(
    val expense: List<Expense> = listOf(),
    val dateStart: LocalDate = LocalDate.now(),
    val dateEnd: LocalDate = LocalDate.now().minusDays(7),
    )
