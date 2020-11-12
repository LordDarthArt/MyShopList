package tk.lorddarthart.myshoplist.app.view.fragment.shopping_list.helper

sealed class MathAction {
    object Minus: MathAction()
    object Plus: MathAction()
}