package uabc.ic.benjaminbolanos.proyectofinalandroid.pojo

import com.google.gson.annotations.SerializedName
import uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.ChallengerEntry

data class ChallengerLeague(
    @SerializedName("tier"     ) var tier     : String?            = null,
    @SerializedName("leagueId" ) var leagueId : String?            = null,
    @SerializedName("queue"    ) var queue    : String?            = null,
    @SerializedName("name"     ) var name     : String?            = null,
    @SerializedName("entries"  ) var entries  : ArrayList<ChallengerEntry> = arrayListOf()
)
