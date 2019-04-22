package com.desafioamedigital

fun category(func: CategoryRobot.() -> Unit) = CategoryRobot().apply{func ()}

class CategoryRobot: BaseRobot(){

    fun clickProductItem() = recyclerViewItemClick(R.id.rv_products)

}