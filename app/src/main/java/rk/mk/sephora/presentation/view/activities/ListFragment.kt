package rk.mk.sephora.presentation.view.activities

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.heetch.technicaltest.presentation.viewmodel.ViewModelFactory
import rk.mk.sephora.R
import rk.mk.sephora.databinding.FragmentListBinding
import rk.mk.sephora.domain.local_model.Product
import rk.mk.sephora.presentation.adapter.ProductListAdapter
import rk.mk.sephora.presentation.viewmodel.ProductsListViewModel
import java.util.*


/**
 * This class main fragment of this app.
 *
 * It extends the [AppCompatActivity] class.
 */
class ListFragment : Fragment(), LifecycleOwner {

    private var mProductListActivityBinding: FragmentListBinding? = null
    private var mProductsListViewModel: ProductsListViewModel? = null
    private lateinit var mProductsListAdapter: ProductListAdapter
    private var isPortrait = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        isPortrait = resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT
        initDataBinding()
        setUpListOfProductsView(mProductListActivityBinding!!.rvProductList)
    }

    /**
     * This function initializes the activity data binding.
     */
    private fun initDataBinding() {
        mProductsListAdapter =
            ProductListAdapter(this.context, arrayListOf(), this) //Empty
        mProductListActivityBinding =
            DataBindingUtil.setContentView(this.requireActivity(), R.layout.fragment_list)
        val factory = ViewModelFactory(this.requireActivity())
        mProductsListViewModel =
            ViewModelProviders.of(this, factory).get(ProductsListViewModel::class.java)
        mProductListActivityBinding!!.productListViewModel = mProductsListViewModel
        mProductsListViewModel!!.mProductLiveData.observe(
            viewLifecycleOwner,
            productLiveDataUpdateObserver
        )
    }

    /**
     * This function sets up the observer of the lifecycle to handle changes.
     */
    private var productLiveDataUpdateObserver =
        androidx.lifecycle.Observer<ArrayList<Product>> { result ->
            mProductsListAdapter =
                mProductListActivityBinding!!.rvProductList.adapter as ProductListAdapter
            mProductsListAdapter.fillList(result)
            mProductListActivityBinding!!.rvProductList.adapter = mProductsListAdapter
        }


    /**
     * This function is called when the screen rotates.
     *
     * @property newConfig
     */
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val layoutManager = LinearLayoutManager(this.context)
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutManager.orientation = LinearLayoutManager.VERTICAL
        }
        mProductListActivityBinding!!.rvProductList.layoutManager = layoutManager
    }

    /**
     * This function sets up the list of products adapter with recycler view.
     * It also builds the Recycler using [LinearLayoutManager]
     *
     * @property productRecyclerView
     */
    private fun setUpListOfProductsView(productRecyclerView: RecyclerView) {
        productRecyclerView.adapter = mProductsListAdapter
        productRecyclerView.layoutManager = LinearLayoutManager(this.context)
    }

    /**
     * This function is called when the activity is destroyed (Lifecycle).
     * It stops the observation process
     */
    override fun onDestroy() {
        super.onDestroy()
        mProductsListViewModel!!.reset()
    }

}