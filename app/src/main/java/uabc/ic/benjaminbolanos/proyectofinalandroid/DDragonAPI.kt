package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.graphics.Bitmap
import android.widget.ImageView
import com.android.volley.toolbox.ImageRequest

class DDragonAPI {

    fun interface Listener{
        fun onSuccess(bitmap: Bitmap)
    }

    private fun imageRequest(imgURL: String, callback: Listener): ImageRequest{
        val imgRequest = ImageRequest(imgURL,
            {bitmap ->
                callback.onSuccess(bitmap)
            },
            0,0,
            ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888
        ) { error ->
            error.printStackTrace()
        }
        return imgRequest
    }

    fun profileIconRequest(iconID:Int?, callback: Listener): ImageRequest{
        val profileIconURL = "https://ddragon.leagueoflegends.com/cdn/12.9.1/img/profileicon/$iconID.png"
        return imageRequest(profileIconURL, callback)
    }

    fun itemIconRequest(itemID: Int?, callback: Listener): ImageRequest{
        val itemIconURL = "http://ddragon.leagueoflegends.com/cdn/12.10.1/img/item/$itemID.png"
        return imageRequest(itemIconURL, callback)
    }

    fun summonerSpellIconRequest(spellId: Int?, callback: Listener): ImageRequest{
        val spellIconURL = "http://ddragon.leagueoflegends.com/cdn/12.10.1/img/spell" +
                "/Summoner${getSpellNameFromId(spellId)}.png"

        return imageRequest(spellIconURL, callback)
    }

    fun runeIconRequest(runeID: Int?, callback: Listener): ImageRequest{
        val runeIconURL = "https://ddragon.canisback.com/img/perk-images/Styles/${getRuneURLFromId(runeID)}"

        return imageRequest(runeIconURL, callback)
    }

    fun championIconRequest(championName:String, callback: Listener): ImageRequest{
        val championIconURL = "http://ddragon.leagueoflegends.com/cdn/12.9.1/img/" +
                "champion/$championName.png"

        return imageRequest(championIconURL, callback)
    }

    private fun getRuneURLFromId(runeID: Int?): String{
        return when(runeID){
            8100 -> "7200_Domination.png"
            8112 -> "Domination/Electrocute/Electrocute.png"
            8124 -> "Domination/Predator/Predator.png"
            8128 -> "Domination/DarkHarvest/DarkHarvest.png"
            9923 -> "Domination/HailOfBlades/HailOfBlades.png"

            8300 -> "7203_Whimsy.png"
            8351 -> "Inspiration/GlacialAugment/GlacialAugment.png"
            8360 -> "Inspiration/UnsealedSpellbook/UnsealedSpellbook.png"
            8369 -> "Inspiration/FirstStrike/FirstStrike.png"

            8000 -> "7201_Precision.png"
            8005 -> "Precision/PressTheAttack/PressTheAttack.png"
            8008 -> "Precision/LethalTempo/LethalTempoTemp.png"
            8021 -> "Precision/FleetFootwork/FleetFootwork.png"
            8010 -> "Precision/Conqueror/Conqueror.png"

            8400 -> "7204_Resolve.png"
            8437 -> "Resolve/GraspOfTheUndying/GraspOfTheUndying.png"
            8439 -> "Resolve/VeteranAftershock/VeteranAftershock.png"
            8465 -> "Resolve/Guardian/Guardian.png"

            8200 -> "7202_Sorcery.png"
            8214 -> "Sorcery/SummonAery/SummonAery.png"
            8229 -> "Sorcery/ArcaneComet/ArcaneComet.png"
            8230 -> "Sorcery/PhaseRush/PhaseRush.png"

            else -> "Domination/Electrocute/Electrocute.png"
        }
    }

    private fun getSpellNameFromId(spellId: Int?): String{
        return when(spellId){
            1  -> "Boost" //Cleanse
            3  -> "Exhaust"
            4  -> "Flash"
            6  -> "Haste" //Ghost
            7  -> "Heal"
            11 -> "Smite"
            12 -> "Teleport"
            13 -> "Mana"
            14 -> "Dot"  //Ignite
            21 -> "Barrier"
            32 -> "Snowball"
            else -> "Summoner_UltBookSmitePlaceholder.png"
        }
    }

}