package com.ryen.spendulum.mock

import androidx.compose.ui.graphics.Color
import com.ryen.spendulum.data.entity.Category
import com.ryen.spendulum.data.entity.Expense
import com.ryen.spendulum.models.Recurrence
import io.github.serpro69.kfaker.Faker
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

val faker = Faker()

val mockCategories = listOf(
    Category(
        "Bills",
        Color(
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255)
        )
    ),
    Category(
        "Subscriptions", Color(
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255)
        )
    ),
    Category(
        "Take out", Color(
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255)
        )
    ),
    Category(
        "Hobbies", Color(
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255),
            faker.random.nextInt(0, 255)
        )
    ),
)

val mockExpenses: List<Expense> = List(30) { index ->
    Expense(
        id = index,
        amount = faker.random.nextInt(min = 1, max = 999)
            .toDouble() + faker.random.nextDouble(),
        date = LocalDateTime.now().minus(
            faker.random.nextInt(min = 300, max = 345600).toLong(),
            ChronoUnit.SECONDS
        ),
        recurrence = faker.random.randomValue(
            listOf(
                Recurrence.None,
                Recurrence.Daily,
                Recurrence.Monthly,
                Recurrence.Weekly,
                Recurrence.Yearly
            )
        ),
        note = faker.name.firstName(),
        category = faker.random.randomValue(mockCategories)
    )
}