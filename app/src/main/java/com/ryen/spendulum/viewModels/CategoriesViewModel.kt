package com.ryen.spendulum.viewModels

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.ryen.spendulum.models.CategoriesState
import com.ryen.spendulum.models.Category
import com.ryen.spendulum.ui.theme.Primary
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
        val newCategoryList = mutableListOf(
            Category(
            _uiState.value.categoryName,
                _uiState.value.categoryColor
            )
        )
        newCategoryList.addAll(_uiState.value.categories)

        _uiState.update {
            it.copy(
                categories = newCategoryList,
                categoryName = "",
                categoryColor = Primary,
            )
        }
    }

    fun deleteCategory(category: Category){
        val newCategoryList = _uiState.value.categories.toMutableList()
        newCategoryList.remove(category)
        _uiState.update {
            it.copy(
                categories = newCategoryList
            )
        }
    }
}