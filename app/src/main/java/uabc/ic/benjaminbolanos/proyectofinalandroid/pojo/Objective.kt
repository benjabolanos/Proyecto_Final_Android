package uabc.ic.benjaminbolanos.proyectofinalandroid.pojo

import com.google.gson.annotations.SerializedName

class Objective(
    @SerializedName("baron"      ) var baron      : Baron?      = Baron(),
    @SerializedName("champion"   ) var champion   : Champion?   = Champion(),
    @SerializedName("dragon"     ) var dragon     : Dragon?     = Dragon(),
    @SerializedName("inhibitor"  ) var inhibitor  : Inhibitor?  = Inhibitor(),
    @SerializedName("riftHerald" ) var riftHerald : RiftHerald? = RiftHerald(),
    @SerializedName("tower"      ) var tower      : Tower?      = Tower()
)

class Baron(
    @SerializedName("first" ) var first : Boolean? = null,
    @SerializedName("kills" ) var kills : Int?     = null
)

class Champion(
    @SerializedName("first" ) var first : Boolean? = null,
    @SerializedName("kills" ) var kills : Int?     = null
)

class Dragon(
    @SerializedName("first" ) var first : Boolean? = null,
    @SerializedName("kills" ) var kills : Int?     = null
)

class Inhibitor(
    @SerializedName("first" ) var first : Boolean? = null,
    @SerializedName("kills" ) var kills : Int?     = null
)

class RiftHerald(
    @SerializedName("first" ) var first : Boolean? = null,
    @SerializedName("kills" ) var kills : Int?     = null
)

class Tower(
    @SerializedName("first" ) var first : Boolean? = null,
    @SerializedName("kills" ) var kills : Int?     = null
)