package rk.mk.sephora.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import rk.mk.sephora.R
import rk.mk.sephora.databinding.RowProductListBinding
import rk.mk.sephora.domain.local_model.Product
import rk.mk.sephora.presentation.view.extra.ProductViewHolder

/**
 * This class represents the list adapter used to show the products.
 * It extends the [RecyclerView.Adapter] class which builds a basic adapter for [RecyclerView].
 * It builds this adapter using the [ProductViewHolder].
 *
 * @property context The context of the application.
 * @property products The list of products.
 *
 * @constructor Creates a products list adapter.
 */
open class ProductListAdapter(
    open var context: Context?,
    open var products: ArrayList<Product>,
    private val fragment: Fragment
) : RecyclerView.Adapter<ProductViewHolder>() {

    /**
     * This function is used to create the view holder.
     *
     * @property parent is the [ViewGroup] holding the elements.
     * @property viewType .
     *
     * @return a [ProductViewHolder]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        //This item represents the object binding the Product view element
        val itemProductBinding: RowProductListBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.row_product_list,
            parent,
            false
        )
        return ProductViewHolder(itemProductBinding)
    }

    /**
     * This function is used to create the view holder.
     *
     * @property holder is the [ProductViewHolder] representing a [Product].
     * @property position of the product in the list of items.
     */
    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        //Ger the product on the given position
        val prodcut = this.products[position]
        //Binds the UI to the product
        holder.bindProduct(prodcut, fragment)
    }

    /**
     * This function count the products in the given list of elements.
     * @return a [Int] of the element count.
     */
    override fun getItemCount(): Int {
        return products.size
    }

    /**
     * This function fills the adapters element list.
     * @property products is the new list of elements.
     */
    fun fillList(products: ArrayList<Product>) {
        //clears the old list
        clear()
        //fills the new list
        this.products.addAll(products)
    }

    /**
     * This function returns the product at a given position in a list.
     * @property position
     * @return [Product]
     */
    fun getItemAtPosition(position: Int): Product {
        return products[position]
    }

    /**
     * This function empties the elements list.
     */
    fun clear() {
        this.products.clear()
    }

}

