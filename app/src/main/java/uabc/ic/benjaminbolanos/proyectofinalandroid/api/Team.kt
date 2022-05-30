package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Team(
    @SerializedName("bans"       ) var bans       : ArrayList<String>? = null,
    @SerializedName("objectives" ) var objectives : Objective?       = Objective(),
    @SerializedName("teamId"     ) var teamId     : Int?              = null,
    @SerializedName("win"        ) var win        : Boolean?          = null
) {
}