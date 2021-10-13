package rk.mk.sephora.presentation.view.activities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import com.heetch.technicaltest.presentation.viewmodel.ViewModelFactory
import rk.mk.sephora.R
import rk.mk.sephora.databinding.FragmentProductBinding
import rk.mk.sephora.presentation.viewmodel.ProductActivityViewModel

class ProductFragment : Fragment(), LifecycleOwner {
    private var mProductActivityBinding: FragmentProductBinding? = null
    private var mProductsActivityViewModel: ProductActivityViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_product, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initDataBinding()
    }

    /**
     * This function initializes the activity data binding.
     */
    private fun initDataBinding() {
        mProductActivityBinding =
            DataBindingUtil.setContentView(requireActivity(), R.layout.fragment_product)
        val factory = ViewModelFactory(requireActivity())
        mProductsActivityViewModel =
            ViewModelProviders.of(this, factory).get(ProductActivityViewModel::class.java)
        mProductActivityBinding!!.productActivityViewModel = mProductsActivityViewModel
    }

}