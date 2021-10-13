package rk.mk.sephora.presentation.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableInt
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.heetch.technicaltest.presentation.util.schedulers.impl.SchedulerProvider
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import rk.mk.sephora.data.remote.remote_model.ItemsRemoteModel
import rk.mk.sephora.data.remote.remote_model.ProductRemoteModel
import rk.mk.sephora.domain.local_model.Product
import rk.mk.sephora.domain.repository.ProductRepositoryImpl
import rk.mk.sephora.domain.usecase.GetProductsUseCase
import java.util.concurrent.TimeUnit


/**
 * This class is the Products List Activity viewModel.
 * It extends the [ViewModel] class.
 *
 * @constructor Creates a [ProductsListViewModel]
 */
class ProductsListViewModel : ViewModel() {

    //Data related variables
    var mProductList = arrayListOf<Product>()
    var mProductLiveData: MutableLiveData<ArrayList<Product>> = MutableLiveData()


    //UI state variables
    var hasProvidedResults = false
    private var mCompositeDisposable = CompositeDisposable()
    var mSpinKitVisibility = ObservableInt(View.VISIBLE)
    var mProductRecyclerVisibility = ObservableInt(View.INVISIBLE)
    var mWelcomeMessageVisibility = ObservableInt(View.INVISIBLE)


    /**
     * This init block is called when the [ProductsViewModel] is created.
     *
     */
    init {
        fakeData()
    }

    private fun fakeData() {
        val items = listOf(
            ProductRemoteModel(
                "73A7F70C-75DA-4C2E-B5A3-EED40DC53AA6",
                "Description 1",
                "Location 1",
                "https://url-1.com"
            ),
            ProductRemoteModel(
                "BA298A85-6275-48D3-8315-9C8F7C1CD109",
                "",
                "Location 2",
                "https://url-2.com"
            ),
            ProductRemoteModel(
                "5A0D45B3-8E26-4385-8C5D-213E160A5E3C",
                "Description 3",
                "",
                "https://url-3.com"
            ),
            ProductRemoteModel(
                "FF0ECFE2-2879-403F-8DBE-A83B4010B340",
                "",
                "",
                "https://url-4.com"
            ),
            ProductRemoteModel(
                "DC97EF5E-2CC9-4905-A8AD-3C351C311001",
                "Description 5",
                "Location 5",
                "https://url-5.com"
            ),
            ProductRemoteModel(
                "557D87F1-25D3-4D77-82E9-364B2ED9CB30",
                "Description 6",
                "Location 6",
                "https://url-6.com"
            ),
            ProductRemoteModel(
                "A83284EF-C2DF-415D-AB73-2A9B8B04950B",
                "Description 7",
                "Location 7",
                "https://url-7.com"
            ),
            ProductRemoteModel(
                "F79BD7F8-063F-46E2-8147-A67635C3BB01",
                "Description 8",
                "Location 8",
                "https://url-8.com"
            )
        )
        val itemObj = ItemsRemoteModel(items)
        val delayedObservable =
            Observable.just(itemObj.items).delay(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { data -> showProducts(ItemsRemoteModel(data)) }
        //mCompositeDisposable.add(delayedObservable)
        //showProducts(itemObj)
    }

    fun callApi() {
        mCompositeDisposable.add(
            GetProductsUseCase(
                ProductRepositoryImpl(),
                SchedulerProvider()
            )
                .buildUseCaseSingle().map {
                    showProducts(it)
                }
                .subscribe())
    }

    /**
     * This function shows the list of available products on the screen.
     *
     * @property remoteProductsList
     *
     */
    private fun showProducts(remoteProductsList: ItemsRemoteModel) {
        Log.d(ProductsListViewModel::javaClass.toString(), "Showing products : $remoteProductsList")
        if (remoteProductsList.items.isNotEmpty()) {
            hasProvidedResults = true
            mProductRecyclerVisibility.set(View.VISIBLE)
            mSpinKitVisibility.set(View.INVISIBLE)
            mWelcomeMessageVisibility.set(View.INVISIBLE)
            updateProductDataList(remoteProductsList.items)
        } else {
            hasProvidedResults = false
            mProductRecyclerVisibility.set(View.INVISIBLE)
            mWelcomeMessageVisibility.set(View.VISIBLE)
        }
    }

    /**
     * This function updates the local data list and notifies the bound activities.
     *
     * @property products
     *
     */
    private fun updateProductDataList(products: List<ProductRemoteModel>) {
        mProductList.clear()
        products.forEach { productRemoteModel ->
            mProductList.add(Product(productRemoteModel))
        }
        mProductLiveData.value = mProductList
    }


    /**
     * This function resets the composite of disposables and frees the observers by clearing everything.
     *
     */
    fun reset() {
        mProductList.clear()
        mProductLiveData.value!!.clear()
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
        mCompositeDisposable.clear()
    }

}

