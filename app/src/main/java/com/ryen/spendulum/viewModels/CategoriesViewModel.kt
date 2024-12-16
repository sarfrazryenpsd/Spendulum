package com.ryen.spendulum.viewModels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.models.CategoriesState
import com.ryen.spendulum.data.entity.Category
import com.ryen.spendulum.data.repository.CategoryRepository
import com.ryen.spendulum.ui.theme.Primary
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoriesViewModel(private val categoryRepository: CategoryRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(CategoriesState())
    val uiState = _uiState.asStateFlow()

    val allCategories: Flow<List<Category>> = categoryRepository.getAllCategories()


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
        val newCategory = Category(
            name = _uiState.value.categoryName,
            color = _uiState.value.categoryColor.value.toInt() // Convert Color to String
        )

        viewModelScope.launch {
            categoryRepository.insertCategory(newCategory) // Save to the database
            // Update UI state after saving
            _uiState.update {
                it.copy(
                    categoryName = "",
                    categoryColor = Primary, // Reset color
                    categories = it.categories.toMutableList()
                        .apply { add(newCategory) } // Add to UI list
                )
            }
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            categoryRepository.deleteCategory(category) // Delete from database
            // Update UI state after deletion
            _uiState.update {
                it.copy(categories = it.categories.toMutableList().apply { remove(category) })
            }
        }
    }
}