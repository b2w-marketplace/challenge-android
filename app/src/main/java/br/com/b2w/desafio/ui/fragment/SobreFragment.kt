package br.com.b2w.desafio.ui.fragment

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.b2w.desafio.R
import br.com.b2w.desafio.databinding.FragmentSobreBinding

class SobreFragment : Fragment() {

    private lateinit var binding: FragmentSobreBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sobre, container, false)

        return binding.root
    }
}