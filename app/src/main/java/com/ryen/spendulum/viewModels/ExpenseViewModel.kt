package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import com.ryen.spendulum.models.ExpenseState
import com.ryen.spendulum.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ExpenseViewModel: ViewModel() {

    private val _uiState = MutableStateFlow(ExpenseState())
    val uiState = _uiState.asStateFlow()

    fun setRecurrence(recurrence: Recurrence) {
        _uiState.update { currentState ->
            currentState.copy(
                recurrence = recurrence
            )
        }
    }
}