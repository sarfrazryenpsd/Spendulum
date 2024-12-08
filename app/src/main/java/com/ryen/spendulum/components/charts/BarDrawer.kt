package com.ryen.spendulum.components.charts

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.DrawScope
import com.github.tehras.charts.bar.BarChartData
import com.github.tehras.charts.bar.renderer.bar.BarDrawer
import com.ryen.spendulum.ui.theme.SystemGray04

class BarDrawer : BarDrawer{
    private val barPaint = Paint().apply {
        this.isAntiAlias = true
    }
    override fun drawBar(
        drawScope: DrawScope,
        canvas: Canvas,
        barArea: Rect,
        bar: BarChartData.Bar
    ) {
        canvas.drawRoundRect(
            barArea.left,
            0f,
            barArea.right + 30f,
            barArea.bottom,
            16f,
            16f,
            paint = barPaint.apply { color = SystemGray04 }
        )
        canvas.drawRoundRect(
            barArea.left,
            barArea.top,
            barArea.right + 30f,
            barArea.bottom,
            16f,
            16f,
            paint = barPaint.apply { color = bar.color }
        )
    }

}