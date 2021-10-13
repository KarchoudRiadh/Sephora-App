package rk.mk.sephora.presentation.viewmodel

import androidx.databinding.ObservableField
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import rk.mk.sephora.domain.local_model.Product

class ProductActivityViewModel(fragmentActivity: FragmentActivity) : ViewModel() {
    //Data related variables
    lateinit var mProduct: Product
    var mProductLiveData: MutableLiveData<Product> = MutableLiveData()

    //UI state variables
    var mProductDescription = ObservableField("")
    var mProductLocation = ObservableField("")

    init {
        val sharedViewModel =
            ViewModelProviders.of(fragmentActivity).get(SharedViewModel::class.java)
        sharedViewModel.data.observe(fragmentActivity, {
            mProductLiveData.value = it
            mProduct = it
            mProductDescription.set(mProduct.description)
            mProductLocation.set(mProduct.location)
        })
    }
}