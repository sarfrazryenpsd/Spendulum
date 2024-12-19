package com.ryen.spendulum.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ryen.spendulum.data.entity.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * from category")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * from category WHERE name = :category")
    fun getCategory(category: String): Flow<Category>

    // Specify the conflict strategy as IGNORE, when the user tries to add an
    // existing Item into the database Room ignores the conflict.
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategory(category: Category)


    @Delete
    suspend fun deleteCategory(category: Category)

    // Delete all categories
    @Query("DELETE FROM category")
    suspend fun deleteAllCategories()
}