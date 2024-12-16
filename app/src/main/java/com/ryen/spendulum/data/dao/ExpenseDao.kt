package com.ryen.spendulum.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.data.entity.ExpenseModel
import kotlinx.coroutines.flow.Flow
import java.time.LocalDateTime

@Dao
interface ExpenseDao {

    @Transaction
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertExpense(expense: Expense)

    @Transaction
    @Query("SELECT * FROM expenses")
    fun getAllExpenses(): Flow<List<ExpenseModel>>

    @Transaction
    @Query("SELECT * FROM expenses WHERE date BETWEEN :startDate AND :endDate")
    suspend fun getExpensesInRange(
        startDate: LocalDateTime,
        endDate: LocalDateTime
    ): List<ExpenseModel>
}