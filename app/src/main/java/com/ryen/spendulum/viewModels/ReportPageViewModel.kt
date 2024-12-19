package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.data.repository.ExpenseRepository
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.models.ReportState
import com.ryen.spendulum.utils.calculateDateRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalTime

class ReportPageViewModel(
    private val expenseRepository: ExpenseRepository,
    private val page: Int,
    val recurrence: Recurrence
): ViewModel() {
    private val _uiState = MutableStateFlow(ReportState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val (start, end, dayInRange) = calculateDateRange(recurrence, page)

            expenseRepository.getAllExpenses()
                .collect { expenses ->
                    // Filter expenses within the date range
                    val expensesInRange = expenses.filter {
                        it.expense.date.toLocalDate() in start..end
                    }

                    val totalInRange = expensesInRange.sumOf { it.expense.amount }
                    val avgPerDay = if (dayInRange > 0) totalInRange / dayInRange.toDouble() else 0.0

                    // Update UI state directly
                    _uiState.update { currentValue ->
                        currentValue.copy(
                            expense = expensesInRange,
                            dateStart = start.atStartOfDay(),
                            dateEnd = end.atTime(LocalTime.MAX),
                            avgPerDay = avgPerDay,
                            totalInRange = totalInRange
                        )
                    }
                }
        }
    }
}