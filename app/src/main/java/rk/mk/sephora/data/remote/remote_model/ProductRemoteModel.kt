package rk.mk.sephora.data.remote.remote_model

import androidx.annotation.Nullable
import com.google.gson.annotations.SerializedName
import rk.mk.sephora.data.JsonDeserializerWithOptions

/**
 * This data class represents the plain object of the Product
 * It is built from the received json of the [ProductInterface].
 *
 * @property id is id of the product
 * @property description is description of the product
 * @property location is last name of the product
 * @property image is url of the profile image of the product
 *
 * @constructor Creates an object of [ProductRemoteModel].
 */
data class ProductRemoteModel(

    @JsonDeserializerWithOptions.FieldRequired @SerializedName("id") var id: String,
    @Nullable @SerializedName("description") var description: String,
    @Nullable @SerializedName("location") var location: String,
    @Nullable @SerializedName("image") var image: String

)