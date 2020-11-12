package tk.lorddarthart.myshoplist.app.view.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.firestore.FirebaseFirestore
import tk.lorddarthart.myshoplist.R
import tk.lorddarthart.myshoplist.app.App
import tk.lorddarthart.myshoplist.app.model.Shop
import tk.lorddarthart.myshoplist.app.model.Thing
import tk.lorddarthart.myshoplist.app.view.fragment.BaseFragment
import tk.lorddarthart.myshoplist.app.view.fragment.shopping_list.ShoppingListFragment
import tk.lorddarthart.myshoplist.app.viewmodel.activity.MainActivityViewModel
import tk.lorddarthart.myshoplist.databinding.MainActivityBinding
import tk.lorddarthart.myshoplist.util.Constants.CARDS_INNER_CONTENT_COLOR
import tk.lorddarthart.myshoplist.util.Constants.COUNT
import tk.lorddarthart.myshoplist.util.Constants.CURRENCY_SYMBOL
import tk.lorddarthart.myshoplist.util.Constants.IMG_URL
import tk.lorddarthart.myshoplist.util.Constants.IS_CHECKED
import tk.lorddarthart.myshoplist.util.Constants.ITEMS_TO_BUY_LIST
import tk.lorddarthart.myshoplist.util.Constants.PRICE
import tk.lorddarthart.myshoplist.util.Constants.SHOPS
import tk.lorddarthart.myshoplist.util.Constants.STORE_NAME
import tk.lorddarthart.myshoplist.util.Constants.TITLE
import tk.lorddarthart.myshoplist.util.Constants.TOOLBAR_COLOR
import tk.lorddarthart.myshoplist.util.Constants.WORK_HOURS_BEGIN
import tk.lorddarthart.myshoplist.util.Constants.WORK_HOURS_END
import java.text.FieldPosition

class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: MainActivityBinding

    val currentFragment: BaseFragment?
        get() {
            return supportFragmentManager.findFragmentById(R.id.fragment_base_container) as BaseFragment
        }

    private val mainActivityViewModel: MainActivityViewModel by lazy {
        ViewModelProvider(this)[MainActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.CURRENT_ACTIVITY = this

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        activityBinding = DataBindingUtil.setContentView(this, R.layout.main_activity)

        start()
    }

    private fun start() {
        App.FIRESTORE_DATABASE = FirebaseFirestore.getInstance()

        App.SHOP_LIST.clear()
        App.WISH_TO_BUY_LIST.clear()

        App.FIRESTORE_DATABASE.collection(SHOPS).get().addOnSuccessListener { shops ->
            for (shop in shops.documents) {
                shop.reference.collection(ITEMS_TO_BUY_LIST).get().addOnSuccessListener { itemsToBuy ->
                    for (itemToBuy in itemsToBuy.documents) {
                        App.WISH_TO_BUY_LIST.add(
                            Thing(
                                title = itemToBuy.getString(TITLE),
                                price = itemToBuy.getDouble(PRICE),
                                count = itemToBuy.getLong(COUNT),
                                currencySymbol = itemToBuy.getString(CURRENCY_SYMBOL),
                                isChecked = itemToBuy.getBoolean(IS_CHECKED)
                            )
                        )

                        if (App.WISH_TO_BUY_LIST.size == itemsToBuy.documents.size && App.SHOP_LIST.size == shops.documents.size) {
                            supportFragmentManager.beginTransaction().replace(R.id.fragment_base_container, ShoppingListFragment()).commitAllowingStateLoss()
                        }
                    }
                }
                App.SHOP_LIST.add(
                    Shop(
                        cardsInnerContentColor = shop.getString(CARDS_INNER_CONTENT_COLOR),
                        imgUrl = shop.getString(IMG_URL),
                        shopName = shop.getString(STORE_NAME),
                        toolBarColor = shop.getString(TOOLBAR_COLOR),
                        workHoursBegin = shop.getLong(WORK_HOURS_BEGIN)?.toInt(),
                        workHoursEnd = shop.getLong(WORK_HOURS_END)?.toInt()
                    )
                )
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear_all_checkboxes -> { mainActivityViewModel.clearSelected() }
            R.id.exit -> { finishAffinity() }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setSelectedShopPosition(position: Long) {
        mainActivityViewModel.setSelectedShopPosition(position)
    }

    fun getSelectedShopPosition(): Long? {
        return mainActivityViewModel.getSelectedShopPosition()
    }
}
