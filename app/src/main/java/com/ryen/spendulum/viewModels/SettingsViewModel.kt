package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ryen.spendulum.data.repository.CategoryRepository
import kotlinx.coroutines.launch

class SettingsViewModel(private val categoryRepository: CategoryRepository): ViewModel() {

    fun deleteALlCategories() {
        viewModelScope.launch {
            categoryRepository.deleteAllCategories()
        }
    }
}