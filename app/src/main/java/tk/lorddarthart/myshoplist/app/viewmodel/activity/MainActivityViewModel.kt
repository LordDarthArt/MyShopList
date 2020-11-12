package tk.lorddarthart.myshoplist.app.viewmodel.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel: ViewModel() {

    private val _selectedShopPosition = MutableLiveData<Long>()
    val selectedShopPosition: LiveData<Long>
        get() = _selectedShopPosition

    fun setSelectedShopPosition(position: Long) {
        _selectedShopPosition.value = position
    }

    fun getSelectedShopPosition(): Long? {
        return _selectedShopPosition.value
    }

    fun clearSelected() {

    }
}