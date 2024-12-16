package com.ryen.spendulum.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ryen.spendulum.data.converter.LocalDateTimeConverter
import com.ryen.spendulum.data.dao.CategoryDao
import com.ryen.spendulum.data.dao.ExpenseDao
import com.ryen.spendulum.data.entity.Category
import com.ryen.spendulum.data.entity.Expense

@Database(entities = [Expense::class, Category::class], version = 1, exportSchema = false)
@TypeConverters(LocalDateTimeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "expense_tracker_db"
                ).build().also { INSTANCE = it }
            }
        }
    }
}
