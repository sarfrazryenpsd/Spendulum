package com.ryen.spendulum.data.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.ryen.spendulum.models.Recurrence
import java.time.LocalDateTime

@Entity(
    tableName = "expenses",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["name"],
            childColumns = ["categoryName"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val amount: Double,
    val date: LocalDateTime,
    val note: String?,
    val categoryName: String,
    val recurrence: String,
)

data class ExpenseModel(
    @Embedded val expense: Expense,
    @Relation(
        parentColumn = "categoryName",
        entityColumn = "name"
    )
    val category: Category
)

data class DayExpenses(
    val expenses: MutableList<ExpenseModel>,
    var total: Double
)


