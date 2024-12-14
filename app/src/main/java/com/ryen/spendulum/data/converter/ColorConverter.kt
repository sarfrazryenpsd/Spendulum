package com.ryen.spendulum.data.converter

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class ColorConverter {
    @TypeConverter
    fun fromColor(color: Color): Long {
        return color.value.toLong() // Convert to ARGB long
    }

    @TypeConverter
    fun toColor(colorLong: Long): Color {
        return Color(colorLong.toInt()) // Convert back to Color
    }
}
