package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Objective(
    @SerializedName("baron"      ) var baron      : Baron?      = Baron(),
    @SerializedName("champion"   ) var champion   : Champion?   = Champion(),
    @SerializedName("dragon"     ) var dragon     : Dragon?     = Dragon(),
    @SerializedName("inhibitor"  ) var inhibitor  : Inhibitor?  = Inhibitor(),
    @SerializedName("riftHerald" ) var riftHerald : RiftHerald? = RiftHerald(),
    @SerializedName("tower"      ) var tower      : Tower?      = Tower()
) {
}

class Baron:ObjectiveItem(){}
class Champion:ObjectiveItem(){}
class Dragon:ObjectiveItem(){}
class Inhibitor:ObjectiveItem(){}
class RiftHerald:ObjectiveItem(){}
class Tower:ObjectiveItem(){}
