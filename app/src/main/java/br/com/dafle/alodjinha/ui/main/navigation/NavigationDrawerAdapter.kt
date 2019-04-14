package br.com.dafle.alodjinha.ui.main.navigation

import br.com.dafle.alodjinha.R
import br.com.dafle.alodjinha.model.NavItem
import br.com.dafle.alodjinha.util.BaseAdapter
import kotlinx.android.synthetic.main.item_menu.view.*

class NavigationDrawerAdapter(val handler: (NavItem) -> Unit): BaseAdapter<NavItem>(R.layout.item_menu, { menu, view ->
    view.ivicon!!.setImageResource(menu.icon)
    view.tvtitle!!.text = menu.title
    view.setOnClickListener { handler(menu) }
})