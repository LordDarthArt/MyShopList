package tk.lorddarthart.myshoplist.util

import tk.lorddarthart.myshoplist.app.App

fun Double.convertToReadablePrice(): String {
    return String.format("%.2f", this)
}

fun getWishToByListSum(): String? {
    var sum = 0.0
    for (item in App.WISH_TO_BUY_LIST) {
        if (item.isChecked!!) {
            sum += item.price!! * item.count!!
        }
    }
    return sum.convertToReadablePrice()
}

fun getCurrentlySelectedShopPosition(): Long? {
    return App.CURRENT_ACTIVITY.getSelectedShopPosition()
}