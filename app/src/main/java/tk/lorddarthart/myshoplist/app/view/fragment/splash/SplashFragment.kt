package tk.lorddarthart.myshoplist.app.view.fragment.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import tk.lorddarthart.myshoplist.app.view.fragment.BaseFragment
import tk.lorddarthart.myshoplist.app.viewmodel.fragment.splash.SplashViewModel
import tk.lorddarthart.myshoplist.databinding.SplashFragmentBinding

class SplashFragment: BaseFragment() {
    private lateinit var splashFragmentBinding: SplashFragmentBinding

    private val storeListFragmentViewModel: SplashViewModel by lazy {
        ViewModelProvider(this)[SplashViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        splashFragmentBinding = SplashFragmentBinding.inflate(inflater, container, false)
        return splashFragmentBinding.root
    }

    override fun initialization() {

    }
}