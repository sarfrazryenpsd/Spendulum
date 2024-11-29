package com.ryen.spendulum.models

import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class AddState(
    val amount: String = "",
    val recurrence: Recurrence = Recurrence.None,
    val date: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy")),
    val notes: String = "",
    val category: String? = null
)