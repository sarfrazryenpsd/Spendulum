package com.ryen.spendulum.data.converter

import androidx.compose.ui.graphics.Color
import androidx.room.TypeConverter

class ColorConverter {
    @TypeConverter
    fun fromColor(color: Color): Int {
        return color.value.toInt() // Convert to ARGB long
    }

    @TypeConverter
    fun toColor(colorLong: Int): Color {
        return Color(colorLong) // Convert back to Color
    }
}
