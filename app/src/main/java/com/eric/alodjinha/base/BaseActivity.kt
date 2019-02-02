package com.eric.alodjinha.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.eric.alodjinha.R

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