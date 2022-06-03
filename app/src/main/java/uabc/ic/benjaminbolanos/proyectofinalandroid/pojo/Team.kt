package uabc.ic.benjaminbolanos.proyectofinalandroid.pojo

import com.google.gson.annotations.SerializedName

class Team(
    @SerializedName("bans"       ) var bans       : ArrayList<Ban>? = null,
    @SerializedName("objectives" ) var objectives : Objective?         = Objective(),
    @SerializedName("teamId"     ) var teamId     : Int?               = null,
    @SerializedName("win"        ) var win        : Boolean?           = null
) {
}

data class Ban(
    @SerializedName("championId" )  var championId : Int? = null,
    @SerializedName("pickTurn"   )  var pickTurn   : Int? = null
)