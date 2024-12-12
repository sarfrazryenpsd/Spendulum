package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.mock.mockExpenses
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.models.ReportState
import com.ryen.spendulum.utils.DateRangeData
import com.ryen.spendulum.utils.calculateDateRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.YearMonth

class ReportPageViewModel(private val page: Int, val recurrence: Recurrence): ViewModel() {
    private val _uiState = MutableStateFlow(ReportState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch (Dispatchers.IO){
            val (start, end, dayInRange) = calculateDateRange(recurrence, page)


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