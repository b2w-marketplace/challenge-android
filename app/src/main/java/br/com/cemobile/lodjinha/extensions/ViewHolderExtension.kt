package br.com.cemobile.lodjinha.extensions

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.animation.AnimationUtils
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.util.ScrollDirection

fun RecyclerView.ViewHolder.applyScrollFromBottomAnimation(
    view: View,
    actualScrollDirection: ScrollDirection
) {
    // Fazer a animacao somente para scroll down
    if (view.animation == null && actualScrollDirection == ScrollDirection.DOWN) {
        val animationId = R.anim.slide_from_bottom
        val animation = AnimationUtils.loadAnimation(view.context, animationId)
        view.animation = animation
    } else if (view.animation != null && actualScrollDirection == ScrollDirection.UP) {
        // remover a animacao configurada para scroll down quando ocorrer scroll up
        view.animation = null
    }
}