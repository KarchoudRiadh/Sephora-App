package rk.mk.sephora.data.remote.repository

import io.reactivex.Single
import rk.mk.sephora.data.remote.remote_model.ItemsRemoteModel
import rk.mk.sephora.data.remote.remote_model.ProductRemoteModel

/**
 * This interface represents the repository used to access and manage the products.
 */
interface ProductRepository {

    /**
     * This function is used to extract the products.
     *
     * @return a [Single] observable containing a [List] of [ProductRemoteModel]
     */
    fun getProducts(): Single<ItemsRemoteModel>
}