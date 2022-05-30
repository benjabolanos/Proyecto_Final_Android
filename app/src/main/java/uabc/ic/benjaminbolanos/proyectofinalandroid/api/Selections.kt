package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Selections(
    @SerializedName("perk" ) var perk : Int? = null,
    @SerializedName("var1" ) var var1 : Int? = null,
    @SerializedName("var2" ) var var2 : Int? = null,
    @SerializedName("var3" ) var var3 : Int? = null
) {
}