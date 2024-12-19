package com.ryen.spendulum.models

import com.ryen.spendulum.data.entity.Category
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AddState(
    val amount: String = "",
    val recurrence: Recurrence = Recurrence.None,
    val date: LocalDateTime = LocalDateTime.now(),
    val notes: String = "",
    val category: Category? = null,
    val categories: List<Category> = emptyList()
)