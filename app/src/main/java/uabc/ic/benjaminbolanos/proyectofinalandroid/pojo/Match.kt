package uabc.ic.benjaminbolanos.proyectofinalandroid.pojo

import com.google.gson.annotations.SerializedName

class Match(
    @SerializedName("metadata" ) var metadata : Metadata? = Metadata(),
    @SerializedName("info"     ) var info     : Info?     = Info()
){

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Match

        if (this.hashCode() != other.hashCode()) return false

        return true
    }

    override fun hashCode(): Int {
        return metadata?.matchId.hashCode()
    }
}

class Metadata(
    @SerializedName("dataVersion"  ) var dataVersion  : String?           = null,
    @SerializedName("matchId"      ) var matchId      : String?           = null,
    @SerializedName("participants" ) var participants : ArrayList<String> = arrayListOf()
)

class Info(
    @SerializedName("gameCreation"       ) var gameCreation       : Long?                    = null,
    @SerializedName("gameDuration"       ) var gameDuration       : Long?                 = null,
    @SerializedName("gameEndTimestamp"   ) var gameEndTimestamp   : Long?                     = null,
    @SerializedName("gameId"             ) var gameId             : Long?                    = null,
    @SerializedName("gameMode"           ) var gameMode           : String?                 = null,
    @SerializedName("gameName"           ) var gameName           : String?                 = null,
    @SerializedName("gameStartTimestamp" ) var gameStartTimestamp : Long?                   = null,
    @SerializedName("gameType"           ) var gameType           : String?                 = null,
    @SerializedName("gameVersion"        ) var gameVersion        : String?                 = null,
    @SerializedName("mapId"              ) var mapId              : Int?                    = null,
    @SerializedName("participants"       ) var participants       : ArrayList<Participant>  = arrayListOf(),
    @SerializedName("platformId"         ) var platformId         : String?                 = null,
    @SerializedName("queueId"            ) var queueId            : Int?                    = null,
    @SerializedName("teams"              ) var teams              : ArrayList<Team>        = arrayListOf(),
    @SerializedName("tournamentCode"     ) var tournamentCode     : String?                 = null
)