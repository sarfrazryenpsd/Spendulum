package com.ryen.spendulum.viewModels

import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.ryen.spendulum.models.CategoriesState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CategoriesViewModel: ViewModel(){
    private val _uiState = MutableStateFlow(CategoriesState())
    val uiState = _uiState.asStateFlow()

    fun setCategoryColor(color: Color) {
        _uiState.update { currState ->
            currState.copy(categoryColor = color)
        }
    }
    fun setCategoryName(name: String) {
        _uiState.update { currState ->
            currState.copy(categoryName = name)
        }
    }
    fun showColorPicker() {
        _uiState.update { currState ->
            currState.copy(colorPickerShowing = true)
        }
    }
    fun hideColorPicker() {
        _uiState.update { currState ->
            currState.copy(colorPickerShowing = false)
        }
    }

    fun createCategory() {
        // TODO:
    }
}