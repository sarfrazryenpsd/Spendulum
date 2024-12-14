package com.ryen.spendulum.models

import androidx.compose.ui.graphics.Color
import com.ryen.spendulum.data.entity.Category
import com.ryen.spendulum.ui.theme.Primary

data class CategoriesState(
    val categoryColor: Color = Primary,
    val categoryName: String = "",
    val colorPickerShowing: Boolean = false,
    val categories: MutableList<Category> = mutableListOf()
)
