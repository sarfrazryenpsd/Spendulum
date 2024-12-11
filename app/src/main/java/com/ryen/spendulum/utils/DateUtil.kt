package com.ryen.spendulum.utils

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun LocalDate.formatDay(): String {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val datePart = this

    return when {
        datePart == today -> "Today"
        datePart == yesterday -> "Yesterday"
        this.year != today.year -> this.format(DateTimeFormatter.ofPattern("EEE, dd MMM yyyy"))
        else -> this.format(DateTimeFormatter.ofPattern("EEE, dd MMM"))
    }
}



fun LocalDateTime.formatTime(): String {
    return this.format(DateTimeFormatter.ofPattern("hh:mm a"))
}
