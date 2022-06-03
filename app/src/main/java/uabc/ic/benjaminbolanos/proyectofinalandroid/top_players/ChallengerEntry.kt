package uabc.ic.benjaminbolanos.proyectofinalandroid.top_players

import com.google.gson.annotations.SerializedName

data class ChallengerEntry(
    @SerializedName("summonerId"   ) var summonerId   : String?  = null,
    @SerializedName("summonerName" ) var summonerName : String?  = null,
    @SerializedName("leaguePoints" ) var leaguePoints : Int?     = null,
    @SerializedName("rank"         ) var rank         : String?  = null,
    @SerializedName("wins"         ) var wins         : Int?     = null,
    @SerializedName("losses"       ) var losses       : Int?     = null,
    @SerializedName("veteran"      ) var veteran      : Boolean? = null,
    @SerializedName("inactive"     ) var inactive     : Boolean? = null,
    @SerializedName("freshBlood"   ) var freshBlood   : Boolean? = null,
    @SerializedName("hotStreak"    ) var hotStreak    : Boolean? = null
): Comparable<ChallengerEntry>{
    override fun compareTo(other: ChallengerEntry): Int {
        return if(this.leaguePoints!! > other.leaguePoints!!)  1

        else if(this.leaguePoints!! == other.leaguePoints!!) 0

        else -1
    }

}