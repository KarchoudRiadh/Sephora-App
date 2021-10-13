package com.heetch.technicaltest.presentation.viewmodel

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import rk.mk.sephora.presentation.viewmodel.ProductActivityViewModel
import rk.mk.sephora.presentation.viewmodel.ProductsListViewModel

/**
 * This class is a view model factory.
 *
 * It extends the [ViewModelProvider.Factory] class.
 */
class ViewModelFactory(fragmentActivity: FragmentActivity) : ViewModelProvider.Factory {
    var mFragmentActivity = fragmentActivity

    /**
     * This function is called to build ViewModel.
     *
     * @param T
     * @property modelClass
     */
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsListViewModel::class.java)) {
            return ProductsListViewModel() as T
        }
        if (modelClass.isAssignableFrom(ProductActivityViewModel::class.java)) {
            return ProductActivityViewModel(mFragmentActivity) as T
        }
        throw IllegalArgumentException("UnknownViewModel")
    }

}