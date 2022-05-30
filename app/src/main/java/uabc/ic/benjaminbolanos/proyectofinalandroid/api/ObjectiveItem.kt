package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

open class ObjectiveItem(
    @SerializedName("first" ) var first : Boolean? = null,
    @SerializedName("kills" ) var kills : Int?     = null
) {
}