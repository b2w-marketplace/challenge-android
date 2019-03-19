package com.danilodequeiroz.lodjinha.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danilodequeiroz.lodjinha.R

val ABOUT_FRAGMENT_TAG = AboutFragment::class.java.name

class AboutFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

}