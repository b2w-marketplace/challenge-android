package br.com.cemobile.lodjinha.util

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator

object AnimationUtil {

    private const val PROPERTY_SCALE_X = "scaleX"
    private const val PROPERTY_SCALE_Y = "scaleY"
    private const val PROPERTY_SCALE_FINAL_VALUE = 1.2f
    private const val PROPERTY_SCALE_INITIAL_VALUE = 1f
    private const val DURATION_DEFAULT = 300L

    fun pulse(view: View) {
        val animatorScaleX = ObjectAnimator.ofFloat(view, PROPERTY_SCALE_X,
                PROPERTY_SCALE_FINAL_VALUE, PROPERTY_SCALE_INITIAL_VALUE)
        val animatorScaleY = ObjectAnimator.ofFloat(view, PROPERTY_SCALE_Y,
                PROPERTY_SCALE_FINAL_VALUE, PROPERTY_SCALE_INITIAL_VALUE)

        val animatorSet = AnimatorSet()
        animatorSet.duration = DURATION_DEFAULT
        animatorSet.setInterpolator(AccelerateDecelerateInterpolator())
        animatorSet.play(animatorScaleX).with(animatorScaleY)
        animatorSet.start()
    }

}