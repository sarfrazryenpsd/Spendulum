package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.data.repository.ExpenseRepository
import com.ryen.spendulum.mock.mockExpenses
import com.ryen.spendulum.models.ExpenseState
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.utils.calculateDateRange
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ExpenseViewModel(private val expenseRepository: ExpenseRepository): ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseState())
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch{
            setRecurrence(Recurrence.Weekly)
            expenseRepository.getAllExpenses().stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            ).collect { expenses ->
                _uiState.update { currentState ->
                    currentState.copy(
                        expenses = expenses
                    )
                }
            }
        }
    }

    fun setRecurrence(recurrence: Recurrence) {
        // Calculate the date range based on the recurrence
        val (start, end) = calculateDateRange(recurrence, 0)

        // Fetch expenses from the repository (replace mockExpenses with real data)
        viewModelScope.launch(Dispatchers.IO) {
            val filteredExpenses = expenseRepository.getExpensesInRange(start, end)

            // Calculate total amount in the date range
            val totalInRange = filteredExpenses.sumOf { it.expense.amount }

            // Update UI state with the new data
            _uiState.update { currentState ->
                currentState.copy(
                    recurrence = recurrence,
                    sumTotal = totalInRange,
                    expenses = filteredExpenses
                )
            }
        }
    }

}