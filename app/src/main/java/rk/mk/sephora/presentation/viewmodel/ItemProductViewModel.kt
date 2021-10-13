package rk.mk.sephora.presentation.viewmodel


import android.view.View
import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import rk.mk.sephora.R
import rk.mk.sephora.domain.local_model.Product
import rk.mk.sephora.presentation.util.image.RxPicasso
import rk.mk.sephora.presentation.view.activities.ProductFragment

/**
 * This class is the Products Item viewModel.
 * It extends the [BaseObservable] interface.
 *
 * @property product related to this view mode.
 * @property context of the application.
 *
 * @constructor Creates a [ItemProductViewModel]
 */
class ItemProductViewModel(var product: Product, var fragmentActivity: Fragment) :
    ViewModel() {

    //Variables bound to the xml (row_product_list)
    var mProductPictureUrl: String = product.image
    var mDescription: String = product.description
    var mLocation: String = product.location

    /**
     * This function is called when a product element has been clicked.
     * Bound to the xml property.
     *
     * @property view
     *
     */
    fun onProductClick(view: View) {
        val sharedViewModel =
            ViewModelProviders.of(fragmentActivity).get(SharedViewModel::class.java)
        sharedViewModel.data.value = product
        val productFrag = ProductFragment()
        val manager = fragmentActivity.childFragmentManager
        val fragmentTransaction = manager.beginTransaction()
        fragmentTransaction.replace(R.id.frameFragmentHome, productFrag)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}

/**
 * This function is called to update the Product Picture.
 * The image is downloaded given a url using Picasso Lib.
 * Bound to the xml property mProductPictureUrl.
 *
 * @property imageView
 * @property url
 *
 */
@BindingAdapter("productImageUrl")
fun setProductImageUrl(imageView: ImageView, url: String?) {
    RxPicasso().loadImage(url!!)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({ bitmap ->
            imageView.setImageBitmap(bitmap)
        }, {
            it.printStackTrace()
            val v = (0..10).random()
            if (v % 2 == 0) imageView.setImageResource(R.drawable.sephora_feature)
            else imageView.setImageResource(R.drawable.product_image_holder)
        })

}