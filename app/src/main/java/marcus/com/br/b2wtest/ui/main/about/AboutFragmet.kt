package marcus.com.br.b2wtest.ui.main.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.toolbar.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.ui.BaseFragment
import marcus.com.br.b2wtest.ui.main.MainActivity

class AboutFragmet : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_about, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setupToolbar()
    }

    private fun setupToolbar() {
        activity?.let {
            val toolbar = (activity as MainActivity).toolbar
            toolbar.findViewById<ImageView>(R.id.toolbarImage).visibility = View.GONE
            toolbar.findViewById<TextView>(R.id.toolbarTitle).text = getString(R.string.title_about)
        }
    }

    companion object {
        const val TAG = "about.tag"
    }
}