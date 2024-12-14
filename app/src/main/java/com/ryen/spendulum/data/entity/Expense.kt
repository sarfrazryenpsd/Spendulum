package com.ryen.spendulum.data.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.ryen.spendulum.models.Recurrence
import java.time.LocalDateTime

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["name"],
            childColumns = ["category"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Double,
    val date: LocalDateTime,
    val note: String?,
    val category: Category,
    val recurrence: Recurrence,
)

data class DayExpenses(
    val expenses: MutableList<Expense>,
    var total: Double
)


