package br.com.dafle.alodjinha.ui.main.navigation

import android.app.Application
import androidx.lifecycle.MutableLiveData
import br.com.dafle.alodjinha.LodjinhaApplication
import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.base.BaseViewModel
import br.com.dafle.alodjinha.model.NavItem
import br.com.dafle.alodjinha.model.NavItemEnum

class NavigationDrawerViewModel(app: Application): BaseViewModel(app) {

    var list = MutableLiveData<List<NavItem>>()

    fun list() {
        val items = mutableListOf<NavItem>()
        items.add(NavItem(R.drawable.home_menu, context.getString(R.string.navigation_drawer_title_home), NavItemEnum.HOME))
        items.add(NavItem(R.drawable.tag_menu, context.getString(R.string.navigation_drawer_about_the_app), NavItemEnum.ABOUT))
        list.value = items
    }
}
