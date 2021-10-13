package rk.mk.sephora.domain.repository

import io.reactivex.Single
import rk.mk.sephora.data.remote.api.ProductInterface
import rk.mk.sephora.data.remote.api.SephoraApi
import rk.mk.sephora.data.remote.remote_model.ItemsRemoteModel
import rk.mk.sephora.data.remote.repository.ProductRepository

/**
 * This class represents an implementation of [ProductRepository] interface used to access and manage the products.
 */
class ProductRepositoryImpl : ProductRepository {

    override fun getProducts(): Single<ItemsRemoteModel> {
        return SephoraApi.retrofit.create(ProductInterface::class.java).getProducts()
    }
}