package marcus.com.br.b2wtest.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.ActionBarDrawerToggle
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.toolbar.*
import marcus.com.br.b2wtest.R
import marcus.com.br.b2wtest.ui.BaseActivity

class HomeActivity : BaseActivity() {

    private val homeViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)
            .get(HomeViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        init()
    }

    private fun init() {
        setupToolBar()
        setupDrawer()
    }

    private fun setupToolBar() {
        setSupportActionBar(toolbar)
    }

    private fun setupDrawer() {
        val toggle = ActionBarDrawerToggle(
            this,
            activityHomeDrawerLayout,
            toolbar,
            R.string.open_drawer,
            R.string.close_drawer
        )

        activityHomeDrawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }
}