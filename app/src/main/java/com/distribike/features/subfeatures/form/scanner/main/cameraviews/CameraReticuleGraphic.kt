package com.distribike.features.subfeatures.form.scanner.main.cameraviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import androidx.core.content.ContextCompat
import com.distribike.R

internal class CameraReticuleGraphic(
    context: Context,
    screenWidth: Int,
    screenHeight: Int,
    private val animator: CameraReticuleAnimator
) : CameraBaseGraphic(context, screenWidth, screenHeight) {

    private val ripplePaint: Paint
    private val rippleSizeOffset: Int
    private val rippleStrokeWidth: Int
    private val rippleAlpha: Int

    init {
        val resources = context.resources
        ripplePaint = Paint()
        ripplePaint.style = Paint.Style.STROKE
        ripplePaint.color = ContextCompat.getColor(context, R.color.grey_500)
        rippleSizeOffset = resources.getDimensionPixelOffset(R.dimen.dimen_40dp)
        rippleStrokeWidth = resources.getDimensionPixelOffset(R.dimen.dimen_4dp)
        rippleAlpha = ripplePaint.alpha
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        // Draws the ripple to simulate the breathing or heartbeat animation effect.
        ripplePaint.alpha = (rippleAlpha * animator.rippleAlphaScale).toInt()
        ripplePaint.strokeWidth = rippleStrokeWidth * animator.rippleStrokeWidthScale
        val offset = rippleSizeOffset * animator.rippleScaleSize
        val rippleRect = RectF(
            boxRect.left - offset,
            boxRect.top - offset,
            boxRect.right + offset,
            boxRect.bottom + offset
        )
        canvas.drawRoundRect(rippleRect, boxCornerRadius, boxCornerRadius, ripplePaint)
    }
}
