package uabc.ic.benjaminbolanos.proyectofinalandroid.pojo

import com.google.gson.annotations.SerializedName

class Perks(
    @SerializedName("statPerks" ) var statPerks : StatPerks?        = StatPerks(),
    @SerializedName("styles"    ) var styles    : ArrayList<Styles> = arrayListOf()
)

class Styles(
    @SerializedName("description" ) var description : String?               = null,
    @SerializedName("selections"  ) var selections  : ArrayList<Selections> = arrayListOf(),
    @SerializedName("style"       ) var style       : Int?                  = null
)

class Selections(
    @SerializedName("perk" ) var perk : Int? = null,
    @SerializedName("var1" ) var var1 : Int? = null,
    @SerializedName("var2" ) var var2 : Int? = null,
    @SerializedName("var3" ) var var3 : Int? = null
)

class StatPerks(
    @SerializedName("defense" ) var defense : Int? = null,
    @SerializedName("flex"    ) var flex    : Int? = null,
    @SerializedName("offense" ) var offense : Int? = null
)