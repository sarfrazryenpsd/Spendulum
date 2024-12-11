package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.mock.mockExpenses
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.models.ReportState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

class ReportViewModel(private val page: Int, val recurrence: Recurrence): ViewModel() {
    private val _uiState = MutableStateFlow(ReportState())
    val uiState = _uiState.asStateFlow()

    private lateinit var start: LocalDate
    private lateinit var end: LocalDate
    private var dayInRange: Int = 0

    init {
        viewModelScope.launch (Dispatchers.IO){
            val today = LocalDate.now()

            when (recurrence) {
                Recurrence.Weekly -> {
                    start = LocalDate.now().minusDays(today.dayOfWeek.value.toLong() -1)
                        .minusDays((page * 7).toLong())
                    end = start.plusDays(6)
                    dayInRange = 7
                }

                Recurrence.Monthly -> {
                    start = LocalDate.of(today.year, today.month, 1)
                        .minusMonths((page * 1).toLong())
                    val daysInMonth = YearMonth.of(start.year, start.month).lengthOfMonth()
                    end = start.plusDays(daysInMonth.toLong() - 1)
                    dayInRange = daysInMonth

                }

                Recurrence.Yearly -> {
                    start = LocalDate.of(today.year, 1, 1)
                    end = LocalDate.of(today.year, 12, 31)
                    dayInRange = 365
                }

                else -> Unit
            }

            val filteredExpenses = mockExpenses.filter { expense ->
                (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate()
                    .isBefore(end)) || expense.date.toLocalDate()
                    .isEqual(start) || expense.date.toLocalDate().isEqual(end)
            }
            val totalInRange = filteredExpenses.sumOf { it.amount }
            val avgPerDay = totalInRange / dayInRange

            viewModelScope.launch (Dispatchers.Main){
                _uiState.update { currentValue ->
                    currentValue.copy(
                        expense = filteredExpenses,
                        dateStart = LocalDateTime.of(start, LocalTime.MIN),
                        dateEnd = LocalDateTime.of(end, LocalTime.MAX),
                        avgPerDay = avgPerDay,
                        totalInRange = totalInRange
                    )
                }
            }
        }
    }
}