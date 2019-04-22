package com.desafioamedigital

fun about(func: AboutAppRobot.() -> Unit) = AboutAppRobot().apply{func ()}

class AboutAppRobot: BaseRobot(){

    fun clickHomeMenuItem() = menuItemClick(R.string.nav_menu_home)

}