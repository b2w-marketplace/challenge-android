package com.caio.lodjinha.base

import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.caio.lodjinha.R

open class BaseActivity : AppCompatActivity() {

    fun addFragment(fragment: Fragment) {

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.content, fragment, fragment.javaClass.simpleName)
            .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
            .addToBackStack(null)
            .commit()
    }
}