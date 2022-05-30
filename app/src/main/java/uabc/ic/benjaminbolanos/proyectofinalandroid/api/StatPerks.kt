package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class StatPerks(

    @SerializedName("defense" ) var defense : Int? = null,
    @SerializedName("flex"    ) var flex    : Int? = null,
    @SerializedName("offense" ) var offense : Int? = null

) {
}