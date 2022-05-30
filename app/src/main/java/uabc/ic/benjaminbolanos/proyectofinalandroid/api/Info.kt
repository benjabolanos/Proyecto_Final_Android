package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Info(
    @SerializedName("gameCreation"       ) var gameCreation       : Long?                    = null,
    @SerializedName("gameDuration"       ) var gameDuration       : Long?                 = null,
    @SerializedName("gameEndTimestamp"   ) var gameEndTimestamp   : Long?                     = null,
    @SerializedName("gameId"             ) var gameId             : Long?                    = null,
    @SerializedName("gameMode"           ) var gameMode           : String?                 = null,
    @SerializedName("gameName"           ) var gameName           : String?                 = null,
    @SerializedName("gameStartTimestamp" ) var gameStartTimestamp : Long?                    = null,
    @SerializedName("gameType"           ) var gameType           : String?                 = null,
    @SerializedName("gameVersion"        ) var gameVersion        : String?                 = null,
    @SerializedName("mapId"              ) var mapId              : Int?                    = null,
    @SerializedName("participants"       ) var participants       : ArrayList<Participant> = arrayListOf(),
    @SerializedName("platformId"         ) var platformId         : String?                 = null,
    @SerializedName("queueId"            ) var queueId            : Int?                    = null,
    @SerializedName("teams"              ) var teams              : ArrayList<Team>        = arrayListOf(),
    @SerializedName("tournamentCode"     ) var tournamentCode     : String?                 = null
) {
}