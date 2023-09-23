package com.distribike.features.subfeatures.form.scanner.main.cameraviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.CornerPathEffect
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import androidx.core.content.ContextCompat
import com.distribike.R

internal abstract class CameraBaseGraphic(
    context: Context,
    screenWidth: Int,
    screenHeight: Int
) : CameraGraphicOverlay.Graphic() {

    private val boxPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.grey_800)
        style = Paint.Style.STROKE
        strokeWidth = context.resources.getDimensionPixelOffset(R.dimen.dimen_4dp).toFloat()
    }

    private val scrimPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.grey_800_opacity20)
    }

    private val eraserPaint: Paint = Paint().apply {
        strokeWidth = boxPaint.strokeWidth
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    val boxCornerRadius = context.resources.getDimensionPixelOffset(R.dimen.dimen_4dp).toFloat()

    val pathPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.purple_200)
        style = Paint.Style.STROKE
        strokeWidth = boxPaint.strokeWidth
        pathEffect = CornerPathEffect(boxCornerRadius)
    }

    val boxRect: RectF = getScannerReticuleBox(screenWidth, screenHeight)

    private fun getScannerReticuleBox(screenWidth: Int, screenHeight: Int): RectF {
        val overlayWidth = screenWidth.toFloat()
        val overlayHeight = screenHeight.toFloat()
        val boxWidth = overlayWidth * 0.85f
        val boxHeight = overlayHeight * 0.15f
        val centerX = overlayWidth / 2f
        val centerY = overlayHeight / 2f
        return RectF(
            centerX - boxWidth / 2f,
            centerY - boxHeight / 2f,
            centerX + boxWidth / 2f,
            centerY + boxHeight / 2f
        )
    }

    override fun draw(canvas: Canvas) {
        // Draws the dark background
        canvas.drawRect(0f, 0f, canvas.width.toFloat(), canvas.height.toFloat(), scrimPaint)
        eraserPaint.style = Paint.Style.FILL
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, eraserPaint)
        eraserPaint.style = Paint.Style.STROKE
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, eraserPaint)
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, boxPaint)
    }
}
