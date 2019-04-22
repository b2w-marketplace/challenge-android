package com.desafioamedigital

fun product(func: ProductRobot.() -> Unit) = ProductRobot().apply{func ()}

class ProductRobot: BaseRobot(){

    fun clickToReserve() = viewClick(R.id.fab_reserve)

    fun checkAlertDialogMessage() = dialogText(R.string.product_details_activity_alert_success_reservation)

}