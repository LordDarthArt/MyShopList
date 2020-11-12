package tk.lorddarthart.myshoplist.app.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import tk.lorddarthart.myshoplist.app.view.activity.MainActivity

abstract class BaseFragment: Fragment() {
    protected lateinit var activity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        activity = context as MainActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    abstract fun initialization()
}