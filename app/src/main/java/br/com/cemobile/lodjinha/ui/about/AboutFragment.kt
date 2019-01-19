package br.com.cemobile.lodjinha.ui.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.cemobile.lodjinha.R
import br.com.cemobile.lodjinha.ui.base.BaseFragment
import br.com.cemobile.lodjinha.ui.home.HomeActivity

class AboutFragment : BaseFragment() {

    companion object {
        fun newInstance() = AboutFragment()
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_about, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
    }

    override fun getTagName(): String = AboutFragment::class.java.simpleName

    private fun setupToolbar() {
        activity?.let {
            val homeActivity = it as HomeActivity
            homeActivity.setTitle(getString(R.string.about))
            homeActivity.hideToolbarLogo()
        }
    }

}