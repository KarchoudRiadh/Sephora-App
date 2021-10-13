package rk.mk.sephora.domain.local_model

import rk.mk.sephora.data.remote.remote_model.ProductRemoteModel
import java.io.Serializable

/**
 * This data class represents the plain object of a product
 * It is a local data object used in the scope of this application.
 *
 * @property id is id of the product
 * @property description is description of the product
 * @property location is last name of the product
 * @property image is url of the profile image of the product
 *
 * @constructor Creates an object of [Product].
 */
class Product : Serializable {
    var id: String
    var description: String
    var location: String
    var image: String

    /**
     * This secondary constructor builds a [Product] object out of a [ProductRemoteModel] object.
     *
     * @param productRemoteModel
     *
     * @constructor Creates an object of [Product].
     */
    constructor(productRemoteModel: ProductRemoteModel) {
        id = productRemoteModel.id
        description = productRemoteModel.description
        location = productRemoteModel.location
        image = productRemoteModel.image
    }

    /**
     * This prints a [Product] object.
     *
     * @return [String]
     */
    override fun toString(): String {
        return "Product(id='$id', description='$description', location='$location', image='$image')"
    }

}