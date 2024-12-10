package com.ryen.spendulum.viewModels

import androidx.lifecycle.ViewModel
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.models.ReportState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.LocalDate
import java.time.YearMonth

class ReportViewModel(val page: Int, val recurrence: Recurrence): ViewModel() {
    private val _uiState = MutableStateFlow(ReportState())
    val uiState = _uiState.asStateFlow()

    init {
        val today = LocalDate.now()
        when(recurrence){
            Recurrence.Weekly -> {
                val start = today.minusDays(today.dayOfWeek.value.toLong() - 1)
                val end = start.plusDays(6)

                _uiState.update { currentValue->
                    currentValue.copy(
                        dateStart = start,
                        dateEnd = end
                    )
                }
            }
            Recurrence.Monthly -> {
                val start = today.minusDays(today.dayOfMonth.toLong() - 1)
                val daysInMonth = YearMonth.of(start.year, start.month).lengthOfMonth()
                val end = start.plusDays(daysInMonth.toLong())

                _uiState.update { currentValue->
                    currentValue.copy(
                        dateStart = start,
                        dateEnd = end
                    )
                }
            }
            Recurrence.Yearly -> {
                val start = LocalDate.of(today.year, 1, 1)
                val end = LocalDate.of(today.year, 12, 31)

                _uiState.update { currentValue->
                    currentValue.copy(
                        dateStart = start,
                        dateEnd = end
                    )
                }
            }
            else -> {}
        }

    }
}