package tk.lorddarthart.myshoplist.app.view.fragment.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tk.lorddarthart.myshoplist.app.view.fragment.main.viewholder.ShopsListViewHolder
import tk.lorddarthart.myshoplist.databinding.ItemRecyclerShopBinding

class ShopsListAdapter: RecyclerView.Adapter<ShopsListViewHolder>() {
    var shopsListBinding: ItemRecyclerShopBinding? = null
    var shopsListViewHolder: ShopsListViewHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopsListViewHolder {
        shopsListBinding = ItemRecyclerShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        shopsListViewHolder = ShopsListViewHolder(shopsListBinding!!)
        return shopsListViewHolder!!
    }

    override fun getItemCount(): Int {
        return 0
    }

    override fun onBindViewHolder(holder: ShopsListViewHolder, position: Int) {

    }
}