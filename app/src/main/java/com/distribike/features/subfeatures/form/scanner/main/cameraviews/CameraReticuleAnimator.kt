package com.distribike.features.subfeatures.form.scanner.main.cameraviews

import android.animation.AnimatorSet
import android.animation.ValueAnimator
import androidx.core.animation.doOnEnd
import androidx.interpolator.view.animation.FastOutSlowInInterpolator

class CameraReticuleAnimator(graphicOverlay: CameraGraphicOverlay) {

    companion object {
        private const val DURATION_RIPPLE_FADE_IN_MS = 333L
        private const val DURATION_RIPPLE_FADE_OUT_MS = 500L
        private const val DURATION_RIPPLE_EXPAND_MS = 833L
        private const val DURATION_RIPPLE_STROKE_WIDTH_SHRINK_MS = 833L
        private const val DURATION_RIPPLE_DORMANCY_MS = 1333L
        private const val START_DELAY_RIPPLE_FADE_OUT_MS = 667L
        private const val START_DELAY_RIPPLE_EXPAND_MS = 333L
        private const val START_DELAY_RIPPLE_STROKE_WIDTH_SHRINK_MS = 333L
        private const val START_DELAY_RESTART_DORMANCY_MSL = 1167L
    }

    var rippleAlphaScale = 0f

    var rippleScaleSize = 0f

    var rippleStrokeWidthScale = 1f
        private set

    private val animatorSet: AnimatorSet

    init {
        val rippleFadeInAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(
            DURATION_RIPPLE_FADE_IN_MS
        )
        rippleFadeInAnimator.addUpdateListener { animation ->
            rippleAlphaScale = animation.animatedValue as Float
            graphicOverlay.postInvalidate()
        }

        val rippleFadeOutAnimator = ValueAnimator.ofFloat(1f, 0f).setDuration(
            DURATION_RIPPLE_FADE_OUT_MS
        )
        rippleFadeOutAnimator.startDelay = START_DELAY_RIPPLE_FADE_OUT_MS
        rippleFadeOutAnimator.addUpdateListener { animation ->
            rippleAlphaScale = animation.animatedValue as Float
            graphicOverlay.postInvalidate()
        }

        val rippleExpandAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(
            DURATION_RIPPLE_EXPAND_MS
        )
        rippleExpandAnimator.startDelay = START_DELAY_RIPPLE_EXPAND_MS
        rippleExpandAnimator.interpolator = FastOutSlowInInterpolator()
        rippleExpandAnimator.addUpdateListener { animation ->
            rippleScaleSize = animation.animatedValue as Float
            graphicOverlay.postInvalidate()
        }

        val rippleStrokeShrinkAnimator = ValueAnimator.ofFloat(1f, 0.5f).setDuration(
            DURATION_RIPPLE_STROKE_WIDTH_SHRINK_MS
        )
        rippleStrokeShrinkAnimator.startDelay = START_DELAY_RIPPLE_STROKE_WIDTH_SHRINK_MS
        rippleStrokeShrinkAnimator.interpolator = FastOutSlowInInterpolator()
        rippleStrokeShrinkAnimator.addUpdateListener { animation ->
            rippleStrokeWidthScale = animation.animatedValue as Float
            graphicOverlay.postInvalidate()
        }

        val fakeAnimatorRestartDelay = ValueAnimator.ofFloat(0f, 0f).setDuration(
            DURATION_RIPPLE_DORMANCY_MS
        )
        fakeAnimatorRestartDelay.startDelay = START_DELAY_RESTART_DORMANCY_MSL
        animatorSet = AnimatorSet()
        animatorSet.playTogether(
            rippleFadeInAnimator,
            rippleFadeOutAnimator,
            rippleExpandAnimator,
            rippleStrokeShrinkAnimator,
            fakeAnimatorRestartDelay
        )
        animatorSet.doOnEnd { it.start() }
    }

    fun start() {
        if (!animatorSet.isRunning) animatorSet.start()
    }

    fun cancel() {
        animatorSet.cancel()
        rippleAlphaScale = 0f
        rippleScaleSize = 0f
        rippleStrokeWidthScale = 0f
    }
}
