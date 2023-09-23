package com.distribike.features.subfeatures.form.scanner.main.cameraviews

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.Point
import android.graphics.PointF
import kotlin.math.abs

internal class CameraLoadingGraphic(
    context: Context,
    screenWidth: Int,
    screenHeight: Int,
    private val animator: ValueAnimator
) : CameraBaseGraphic(context, screenWidth, screenHeight) {

    private val boxClockwiseCoordinates: Array<PointF> = arrayOf(
        PointF(boxRect.left, boxRect.top),
        PointF(boxRect.right, boxRect.top),
        PointF(boxRect.right, boxRect.bottom),
        PointF(boxRect.left, boxRect.bottom)
    )

    private val coordinateOffsetBits: Array<Point> = arrayOf(
        Point(1, 0),
        Point(0, 1),
        Point(-1, 0),
        Point(0, -1)
    )

    private val lastPathPoint = PointF()

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        // Draws the path to simulate the loading animation effect.
        val boxPerimeter = (boxRect.width() + boxRect.height()) * 2
        val path = Path()

        // The distance between the box's left-top corner and the starting point of the white colored path.
        var offsetLength = boxPerimeter * animator.animatedValue as Float % boxPerimeter
        var i = 0
        while (i < 4) {
            val edgeLength = if (i % 2 == 0) boxRect.width() else boxRect.height()
            if (offsetLength <= edgeLength) {
                lastPathPoint.x =
                    boxClockwiseCoordinates[i].x + coordinateOffsetBits[i].x * offsetLength
                lastPathPoint.y =
                    boxClockwiseCoordinates[i].y + coordinateOffsetBits[i].y * offsetLength

                path.moveTo(lastPathPoint.x, lastPathPoint.y)
                break
            }
            offsetLength -= edgeLength
            i++
        }

        // Computes the path based on the determined starting point and path length.
        var pathLength = boxPerimeter * animator.animatedValue as Float
        for (j in 0..3) {
            val index = (i + j) % 4
            val nextIndex = (i + j + 1) % 4
            // The length between path's current end point and reticule box's next coordinate point.
            val lineLength = abs(boxClockwiseCoordinates[nextIndex].x - lastPathPoint.x) +
                    abs(boxClockwiseCoordinates[nextIndex].y - lastPathPoint.y)
            if (lineLength >= pathLength) {
                path.lineTo(
                    lastPathPoint.x + pathLength * coordinateOffsetBits[index].x,
                    lastPathPoint.y + pathLength * coordinateOffsetBits[index].y
                )
                break
            }

            lastPathPoint.x = boxClockwiseCoordinates[nextIndex].x
            lastPathPoint.y = boxClockwiseCoordinates[nextIndex].y
            path.lineTo(lastPathPoint.x, lastPathPoint.y)
            pathLength -= lineLength
        }

        canvas.drawPath(path, pathPaint)
    }
}
