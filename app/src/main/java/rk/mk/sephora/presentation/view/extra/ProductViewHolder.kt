package rk.mk.sephora.presentation.view.extra

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import rk.mk.sephora.databinding.RowProductListBinding
import rk.mk.sephora.domain.local_model.Product
import rk.mk.sephora.presentation.viewmodel.ItemProductViewModel
import java.util.*

/**
 * This class the Products View Holder.
 *
 * @property rowProductListBinding is the binding to the ui element of a product.
 * It extends the Recycler View Holder Class.
 *
 * @constructor Creates a [ProductViewHolder]
 */
class ProductViewHolder(rowProductListBinding: RowProductListBinding) :
    RecyclerView.ViewHolder(rowProductListBinding.root) {

    var mItemProductBinding: RowProductListBinding? = rowProductListBinding

    /**
     * This function binds the product to its appropriate view model.
     *
     * @property product
     */
    fun bindProduct(product: Product, fragment: Fragment) {
        if (mItemProductBinding!!.productViewModel == null) {
            mItemProductBinding!!.productViewModel = ItemProductViewModel(product, fragment)
        } else {
            mItemProductBinding!!.productViewModel!!.product = product
        }
    }
}