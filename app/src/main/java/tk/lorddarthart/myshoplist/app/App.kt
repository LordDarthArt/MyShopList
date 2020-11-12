package tk.lorddarthart.myshoplist.app

import android.app.Application
import com.google.firebase.firestore.FirebaseFirestore
import tk.lorddarthart.myshoplist.app.model.Shop
import tk.lorddarthart.myshoplist.app.model.Thing
import tk.lorddarthart.myshoplist.app.view.activity.MainActivity

class App: Application() {
    companion object {
        // Lists
        var SHOP_LIST = mutableListOf<Shop>()
        val WISH_TO_BUY_LIST = mutableListOf<Thing>()

        // Other
        lateinit var CURRENT_ACTIVITY: MainActivity
        lateinit var FIRESTORE_DATABASE: FirebaseFirestore
    }
}