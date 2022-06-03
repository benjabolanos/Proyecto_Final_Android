package uabc.ic.benjaminbolanos.proyectofinalandroid.pojo

import com.google.gson.annotations.SerializedName

data class Summoner (
    @SerializedName("id"            ) var id            : String? = null,
    @SerializedName("accountId"     ) var accountId     : String? = null,
    @SerializedName("puuid"         ) var puuid         : String? = null,
    @SerializedName("name"          ) var name          : String? = null,
    @SerializedName("profileIconId" ) var profileIconId : Int?    = null,
    @SerializedName("revisionDate"  ) var revisionDate  : Long?    = null,
    @SerializedName("summonerLevel" ) var summonerLevel : Long?    = null
){

}