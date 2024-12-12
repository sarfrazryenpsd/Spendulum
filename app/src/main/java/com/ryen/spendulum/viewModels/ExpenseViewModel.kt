package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.mock.mockExpenses
import com.ryen.spendulum.models.ExpenseState
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.utils.calculateDateRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpenseViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO){
            setRecurrence(Recurrence.Daily)
        }
    }

    fun setRecurrence(recurrence: Recurrence) {
        val (start, end) = calculateDateRange(recurrence, 0)

        val filteredExpenses = mockExpenses.filter { expense ->
            (expense.date.toLocalDate().isAfter(start) && expense.date.toLocalDate()
                .isBefore(end)) || expense.date.toLocalDate()
                .isEqual(start) || expense.date.toLocalDate().isEqual(end)
        }
        val totalInRange = filteredExpenses.sumOf { it.amount }

        _uiState.update { currentState ->
            currentState.copy(
                recurrence = recurrence,
                sumTotal = totalInRange,
                expenses = filteredExpenses
            )
        }
    }

    fun openDropdown(){
        _uiState.update { currentState ->
            currentState.copy(
                isOpen = true
            )
        }
    }
    fun closeDropDown(){
        _uiState.update { currentState ->
            currentState.copy(
                isOpen = false
            )
        }
    }
}