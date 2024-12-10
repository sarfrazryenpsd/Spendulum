package com.ryen.spendulum.components.charts

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.nativeCanvas
import com.github.tehras.charts.bar.renderer.label.LabelDrawer
import com.github.tehras.charts.piechart.utils.toLegacyInt
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.ui.theme.LabelSecondary

class LabelDrawer (val recurrence: Recurrence): LabelDrawer {

    private val leftOffset = when(recurrence){
        Recurrence.Weekly -> 57f
        Recurrence.Monthly -> 14f
        Recurrence.Yearly -> 36f
        else -> 0f
    }
    private val bottomOffset = when(recurrence){
        Recurrence.Weekly -> 65f
        Recurrence.Monthly -> 50f
        Recurrence.Yearly -> 50f
        else -> 0f
    }
    private val tSize = when(recurrence){
        Recurrence.Weekly -> 42f
        Recurrence.Monthly -> 36f
        Recurrence.Yearly -> 38f
        else -> 0f
    }
    private val paint = android.graphics.Paint().apply {
        this.textAlign = android.graphics.Paint.Align.CENTER
        this.color = LabelSecondary.toLegacyInt()
        this.textSize = tSize
        this.isAntiAlias = true
    }
    override fun drawLabel(
        drawScope: DrawScope,
        canvas: Canvas,
        label: String,
        barArea: Rect,
        xAxisArea: Rect
    ) {
        if(recurrence == Recurrence.Monthly && (Integer.parseInt(label) % 5 == 0 || Integer.parseInt(label) == 1) || recurrence != Recurrence.Monthly){
            canvas.nativeCanvas.drawText(
                label,
                barArea.left + leftOffset,
                barArea.bottom + bottomOffset,
                paint
            )
        }
    }
}