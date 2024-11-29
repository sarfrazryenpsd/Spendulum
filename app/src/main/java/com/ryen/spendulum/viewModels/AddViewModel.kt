package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import com.ryen.spendulum.models.AddState
import com.ryen.spendulum.models.Recurrence
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.format.DateTimeFormatter



class AddViewModel: ViewModel() {
    private val _state = MutableStateFlow(AddState())
    val state = _state.asStateFlow()

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
            val formatter = DateTimeFormatter.ofPattern("dd MMM yyyy")
            it.copy(date = date.format(formatter))
        }
    }
    fun setNote(note: String)   {
        _state.update {
            it.copy(notes = note)
        }
    }
    fun setCategory(category: String)   {
        _state.update {
            it.copy(category = category)
        }
    }
}

