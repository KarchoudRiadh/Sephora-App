package rk.mk.sephora.data.remote.api

import io.reactivex.Single
import retrofit2.http.GET
import rk.mk.sephora.data.remote.remote_model.ItemsRemoteModel
import rk.mk.sephora.data.remote.remote_model.ProductRemoteModel

/**
 * This interface represents the endpoints used to manage products.
 */
interface ProductInterface {
    /**
     * This function represents the endpoint used to extract the products.
     *
     * @return a [Single] observable containing a [List] of [ProductRemoteModel]
     */
    @GET("/items.json")
    fun getProducts(): Single<ItemsRemoteModel>
}