package rk.mk.sephora.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import rk.mk.sephora.domain.local_model.Product

class SharedViewModel : ViewModel() {
    var data = MutableLiveData<Product>()

    fun data(item: Product) {
        data.value = item
    }
}