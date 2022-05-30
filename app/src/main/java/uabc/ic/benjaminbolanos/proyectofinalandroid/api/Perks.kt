package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Perks(
    @SerializedName("statPerks" ) var statPerks : StatPerks?        = StatPerks(),
    @SerializedName("styles"    ) var styles    : ArrayList<Styles> = arrayListOf()
) {
}