package rk.mk.sephora.data.remote.remote_model

import com.google.gson.annotations.SerializedName
import rk.mk.sephora.data.JsonDeserializerWithOptions

data class ItemsRemoteModel(
    @JsonDeserializerWithOptions.FieldRequired @SerializedName("items") val items: List<ProductRemoteModel>
)