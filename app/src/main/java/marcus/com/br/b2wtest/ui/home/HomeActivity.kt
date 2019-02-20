package marcus.com.br.b2wtest.ui.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
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
    }
}