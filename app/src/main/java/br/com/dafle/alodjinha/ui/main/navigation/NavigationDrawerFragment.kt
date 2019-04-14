package br.com.dafle.alodjinha.ui.main.navigation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.model.NavItem
import kotlinx.android.synthetic.main.drawer_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class NavigationDrawerFragment : Fragment() {

    val viewModel: NavigationDrawerViewModel by viewModel()
    private var mCallbacks: NavigationDrawerCallbacks? = null
    private var mDrawerLayout: DrawerLayout? = null
    private var mFragmentContainerView: View? = null

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.drawer_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = NavigationDrawerAdapter {
            mDrawerLayout?.apply {
                closeDrawer(mFragmentContainerView!!)
            }
            mCallbacks?.apply {
                onNavigationDrawerItemSelected(it)
            }
        }
        recyclerView.let {
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
        viewModel.list()
        viewModel.list.observe(this, Observer { menus ->
            adapter.setItems(menus)
        })
    }

    fun setUp(fragmentId: Int, drawerLayout: DrawerLayout) {
        mFragmentContainerView = activity!!.findViewById(fragmentId)
        mDrawerLayout = drawerLayout
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            mCallbacks = context as NavigationDrawerCallbacks?
        } catch (e: ClassCastException) {
            throw ClassCastException("Activity must implement NavigationDrawerCallbacks.")
        }
    }

    override fun onDetach() {
        super.onDetach()
        mCallbacks = null
    }

    interface NavigationDrawerCallbacks {
        fun onNavigationDrawerItemSelected(item: NavItem)
    }
}
