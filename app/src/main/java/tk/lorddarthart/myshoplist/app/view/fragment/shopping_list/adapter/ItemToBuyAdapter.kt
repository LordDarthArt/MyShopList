package tk.lorddarthart.myshoplist.app.view.fragment.shopping_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.myshoplist.app.App
import tk.lorddarthart.myshoplist.app.view.fragment.shopping_list.ShoppingListFragment
import tk.lorddarthart.myshoplist.app.view.fragment.shopping_list.helper.MathAction
import tk.lorddarthart.myshoplist.app.view.fragment.shopping_list.viewholder.ItemToBuyViewHolder
import tk.lorddarthart.myshoplist.databinding.ItemRecyclerBuyBinding
import tk.lorddarthart.myshoplist.util.convertToReadablePrice

class ItemToBuyAdapter: RecyclerView.Adapter<ItemToBuyViewHolder>() {
    private lateinit var itemToBuyViewHolder: ItemToBuyViewHolder

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemToBuyViewHolder {
        val itemToBuyViewBinding = ItemRecyclerBuyBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        itemToBuyViewHolder = ItemToBuyViewHolder(itemToBuyViewBinding)
        return itemToBuyViewHolder
    }

    override fun getItemCount(): Int {
        return App.WISH_TO_BUY_LIST.size
    }

    override fun onBindViewHolder(holder: ItemToBuyViewHolder, position: Int) {
        holder.itemToBuyView.itemTitle.text = App.WISH_TO_BUY_LIST[position].title
        holder.itemToBuyView.itemPrice.text = App.WISH_TO_BUY_LIST[position].price!!.convertToReadablePrice()
        holder.itemToBuyView.itemCount.text = App.WISH_TO_BUY_LIST[position].count.toString()
        holder.itemToBuyView.itemRemove.setOnClickListener{ App.WISH_TO_BUY_LIST.removeAt(position); notifyDataSetChanged() }
        holder.itemToBuyView.itemIsChecked.isChecked = App.WISH_TO_BUY_LIST[position].isChecked!!
        holder.itemToBuyView.itemCurrencySymbol.text = App.WISH_TO_BUY_LIST[position].currencySymbol

        holder.itemToBuyView.itemMore.setOnClickListener{
            changeTheItemsCount(position, MathAction.Plus)
        }

        holder.itemToBuyView.itemLess.setOnClickListener{
            changeTheItemsCount(position, MathAction.Minus)
        }

        holder.itemToBuyView.itemIsChecked.setOnCheckedChangeListener{ _, _ ->
            App.WISH_TO_BUY_LIST[position].isChecked = !App.WISH_TO_BUY_LIST[position].isChecked!!
            holder.itemToBuyView.itemIsChecked.isChecked = App.WISH_TO_BUY_LIST[position].isChecked!!
            (App.CURRENT_ACTIVITY.currentFragment as ShoppingListFragment).updateSum()
        }
    }

    private fun changeTheItemsCount(position: Int, mathAction: MathAction) {
        when (mathAction) {
            MathAction.Plus -> { App.WISH_TO_BUY_LIST[position].count = App.WISH_TO_BUY_LIST[position].count!! + 1; notifyDataSetChanged(); (App.CURRENT_ACTIVITY.currentFragment as ShoppingListFragment).updateSum() }
            MathAction.Minus -> { App.WISH_TO_BUY_LIST[position].count = App.WISH_TO_BUY_LIST[position].count!! - 1; notifyDataSetChanged(); (App.CURRENT_ACTIVITY.currentFragment as ShoppingListFragment).updateSum() }
        }
    }
}