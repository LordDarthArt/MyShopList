package tk.lorddarthart.myshoplist.app.model

data class Thing(
    var title: String?,
    var price: Double?,
    var currencySymbol: String?,
    var count: Long?,
    var isChecked: Boolean?
)