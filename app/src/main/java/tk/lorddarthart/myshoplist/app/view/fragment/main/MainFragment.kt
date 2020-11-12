package tk.lorddarthart.myshoplist.app.view.fragment.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import tk.lorddarthart.myshoplist.app.view.fragment.BaseFragment
import tk.lorddarthart.myshoplist.databinding.MainFragmentBinding

class MainFragment: BaseFragment() {
    private lateinit var mainFragmentBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)
        return mainFragmentBinding.root
    }

    override fun initialization() {
        with (mainFragmentBinding) {
            mainFragmentShopsList.layoutManager = LinearLayoutManager(activity)
        }
    }
}