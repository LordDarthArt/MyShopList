package tk.lorddarthart.myshoplist.app.view.fragment.shopping_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import tk.lorddarthart.myshoplist.app.App
import tk.lorddarthart.myshoplist.app.view.fragment.BaseFragment
import tk.lorddarthart.myshoplist.app.view.fragment.shopping_list.adapter.ItemToBuyAdapter
import tk.lorddarthart.myshoplist.app.viewmodel.fragment.shopping_list.ShoppingListViewModel
import tk.lorddarthart.myshoplist.databinding.ShoppingListFragmentBinding
import tk.lorddarthart.myshoplist.util.getCurrentlySelectedShopPosition
import tk.lorddarthart.myshoplist.util.getWishToByListSum

class ShoppingListFragment: BaseFragment() {
    private lateinit var fragmentBinding: ShoppingListFragmentBinding

    private val shoppingListViewModel: ShoppingListViewModel by lazy {
        ViewModelProvider(this)[ShoppingListViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentBinding = ShoppingListFragmentBinding.inflate(inflater, container, false)

        activity.setSupportActionBar(fragmentBinding.fragmentMainToolbar)

        return fragmentBinding.root
    }

    override fun initialization() {
        activity.supportActionBar?.title = App.SHOP_LIST[getCurrentlySelectedShopPosition()?.toInt() ?: 0].shopName

        fragmentBinding.fragmentMainBuyListRecycler.layoutManager = LinearLayoutManager(activity)
        fragmentBinding.fragmentMainBuyListRecycler.adapter = ItemToBuyAdapter()

        fragmentBinding.fragmentMainFinalSumCurrency.text = App.WISH_TO_BUY_LIST[0].currencySymbol
        updateSum()
    }

    fun updateSum() {
        fragmentBinding.fragmentMainFinalSumValue.text = getWishToByListSum()
    }
}