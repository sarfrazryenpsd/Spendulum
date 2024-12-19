package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.data.entity.Category
import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.data.repository.CategoryRepository
import com.ryen.spendulum.data.repository.ExpenseRepository
import com.ryen.spendulum.models.AddState
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.utils.formatToTwoDecimalPlaces
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime


class AddViewModel(
    private val expenseRepository: ExpenseRepository,
    categoryRepository: CategoryRepository
): ViewModel() {
    private val _state = MutableStateFlow(AddState())
    val state = _state.asStateFlow()

    private val allCategories: Flow<List<Category>> = categoryRepository.getAllCategories()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            allCategories.collect { categories ->
                _state.update { currState ->
                    currState.copy(categories = categories)
                }
            }
        }
    }

    fun setAmount(amount: String) {
        _state.update {
            it.copy(amount = amount.trim())
        }
    }
    fun setRecurrence(recurrence: Recurrence)  {
        _state.update {
            it.copy(recurrence = recurrence)
        }
    }
    fun setDate(date: LocalDate)   {
        _state.update {
            val currentTime = LocalTime.now() // Get the current time
            val dateTime = LocalDateTime.of(date, currentTime) // Combine date and time
            it.copy(date = dateTime)
        }
    }
    fun setNote(note: String)   {
        _state.update {
            it.copy(notes = note)
        }
    }
    fun setCategory(category: Category)   {
        _state.update {
            it.copy(category = category)
        }
    }

    fun addExpense(){
        val currentState = _state.value

        val amount = currentState.amount.toDoubleOrNull()?.formatToTwoDecimalPlaces()?.toDoubleOrNull()
        val date = try {
            currentState.date
        } catch (e: Exception) {
            throw e
        }
        val category = currentState.category

        if (amount == null || category == null) {
            // Handle invalid input, e.g., show error in UI
            return
        }
        val newExpense = Expense(
            id = 0,
            amount = amount,
            date = date,
            categoryName = category.name,
            note = currentState.notes,
            recurrence = currentState.recurrence.name
        )

        viewModelScope.launch {
            expenseRepository.insertExpense(newExpense)

            _state.update { currState ->
                currState.copy(
                    amount = "",
                    date = LocalDateTime.now(),
                    notes = "",
                    category = null,
                    recurrence = Recurrence.None
                )
            }
        }

    }


}

