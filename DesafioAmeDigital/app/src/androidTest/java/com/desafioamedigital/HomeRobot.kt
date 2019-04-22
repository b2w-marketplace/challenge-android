package com.desafioamedigital

fun home(func: HomeRobot.() -> Unit) = HomeRobot().apply{func ()}

class HomeRobot: BaseRobot(){

    fun clickAboutMenuItem() = menuItemClick(R.string.nav_menu_about_app)

    fun clickCategoriesItem() = recyclerViewItemClick(R.id.rv_categories)

    fun clickBestSellersItem() = recyclerViewItemClick(R.id.rv_best_sellers)

}