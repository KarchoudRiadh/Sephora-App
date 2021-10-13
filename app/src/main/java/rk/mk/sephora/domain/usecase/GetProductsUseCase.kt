package rk.mk.sephora.domain.usecase

import com.heetch.technicaltest.presentation.util.schedulers.interfaces.BaseSchedulerProvider
import io.reactivex.Single
import rk.mk.sephora.data.remote.remote_model.ItemsRemoteModel
import rk.mk.sephora.data.remote.remote_model.ProductRemoteModel
import rk.mk.sephora.data.remote.repository.ProductRepository

/**
 * This class represents the use case of getting the products list using an observer.
 *
 * @property repository The interface of the products repository.
 * @property schedulerProvider The interface of the schedulers used to run the [Single] observable.
 *
 * @constructor Creates a product use case.
 */
class GetProductsUseCase constructor(
    private val repository: ProductRepository,
    private val schedulerProvider: BaseSchedulerProvider
) {
    /**
     * This function pulls products lists.
     * @return a [Single] observable containing a [List] of [ProductRemoteModel]
     */
    fun buildUseCaseSingle(
    ): Single<ItemsRemoteModel> {
        return repository.getProducts()
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
    }
}