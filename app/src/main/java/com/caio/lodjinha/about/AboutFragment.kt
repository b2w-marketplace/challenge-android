package com.caio.lodjinha.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.caio.lodjinha.R
import com.caio.lodjinha.base.BaseFragment

class AboutFragment : BaseFragment() {

    companion object {

        var aboutFragment: AboutFragment? = null

        fun getInstance(): AboutFragment {

            if (aboutFragment == null) aboutFragment = AboutFragment()
            return aboutFragment!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }
}