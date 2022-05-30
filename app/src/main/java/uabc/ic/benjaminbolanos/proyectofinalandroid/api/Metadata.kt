package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Metadata(
    @SerializedName("dataVersion"  ) var dataVersion  : String?           = null,
    @SerializedName("matchId"      ) var matchId      : String?           = null,
    @SerializedName("participants" ) var participants : ArrayList<String> = arrayListOf()
) {
}