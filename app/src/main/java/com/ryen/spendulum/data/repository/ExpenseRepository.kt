package com.ryen.spendulum.data.repository

import com.ryen.spendulum.data.dao.ExpenseDao
import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.data.entity.ExpenseModel
import java.time.LocalDate

class ExpenseRepository(private val expenseDao: ExpenseDao) {
    suspend fun insertExpense(expense: Expense) = expenseDao.insertExpense(expense)
    suspend fun getAllExpenses() = expenseDao.getAllExpenses()
    suspend fun getExpensesInRange(start: LocalDate, end: LocalDate): List<ExpenseModel> {
        return expenseDao.getExpensesInRange(start.atStartOfDay(), end.atTime(23, 59, 59))
    }
}
