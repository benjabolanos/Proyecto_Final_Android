package uabc.ic.benjaminbolanos.proyectofinalandroid.api

import com.google.gson.annotations.SerializedName

class Styles(
    @SerializedName("description" ) var description : String?               = null,
    @SerializedName("selections"  ) var selections  : ArrayList<Selections> = arrayListOf(),
    @SerializedName("style"       ) var style       : Int?                  = null
) {
}