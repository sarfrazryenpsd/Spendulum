package com.ryen.spendulum.data.repository

import com.ryen.spendulum.data.dao.CategoryDao
import com.ryen.spendulum.data.entity.Category

class CategoryRepository(private val categoryDao: CategoryDao) {
    suspend fun insertCategory(category: Category) = categoryDao.insertCategory(category)
    suspend fun deleteCategory(category: Category) = categoryDao.deleteCategory(category)
    fun getAllCategories() = categoryDao.getAllCategories()
}
