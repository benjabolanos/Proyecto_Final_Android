package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Match(
    @SerializedName("metadata" ) var metadata : Metadata? = Metadata(),
    @SerializedName("info"     ) var info     : Info?     = Info()
) {
}
object ext{
    val matchList: ArrayList<Match> = ArrayList(10)
}