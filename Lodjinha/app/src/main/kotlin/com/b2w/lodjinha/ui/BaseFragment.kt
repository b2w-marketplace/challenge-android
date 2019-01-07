package com.b2w.lodjinha.ui

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.b2w.lodjinha.R

abstract class BaseFragment : Fragment() {

    fun showDefaultError(){
        Toast.makeText(this.activity, R.string.default_error_message, Toast.LENGTH_LONG).show()
    }
}
