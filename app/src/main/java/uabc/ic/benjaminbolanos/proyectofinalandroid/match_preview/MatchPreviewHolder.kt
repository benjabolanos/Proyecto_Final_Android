package uabc.ic.benjaminbolanos.proyectofinalandroid.match_preview

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.view.View
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import uabc.ic.benjaminbolanos.proyectofinalandroid.R


class MatchPreviewHolder(view: View): RecyclerView.ViewHolder(view) {

    val winnerStatus = view.findViewById<View>(R.id.match_item_win_status)
    val championIcon = view.findViewById<ImageView>(R.id.match_item_champion_icon)
    val itemsIcons = view.findViewById<GridLayout>(R.id.match_item_items_icons)
    val kills = view.findViewById<TextView>(R.id.match_item_kills)
    val assists = view.findViewById<TextView>(R.id.match_item_assists)
    val deaths = view.findViewById<TextView>(R.id.match_item_deaths)
    val kp = view.findViewById<TextView>(R.id.match_item_kill_participation)
    val duration = view.findViewById<TextView>(R.id.match_item_duration)
    val type = view.findViewById<TextView>(R.id.match_item_type)
    val spree = view.findViewById<TextView>(R.id.match_item_spree)

    val spells = arrayOf<ShapeableImageView>(view.findViewById(R.id.match_item_spell_1),
        view.findViewById(R.id.match_item_spell_2))

    val runes = arrayOf<ShapeableImageView>(view.findViewById(R.id.match_item_rune_1),
        view.findViewById(R.id.match_item_rune_2))


    @SuppressLint("SetTextI18n")
    fun render(match: MatchPreview){

        //Cambia color del status gano/perdio
        winnerStatus.apply {
            var color = resources.getColor(R.color.rojo_derrota, null)
            if(match.matchWon){
                color = resources.getColor(R.color.azul_victoria, null)
            }
            backgroundTintList = ColorStateList.valueOf(color)
            backgroundTintMode = PorterDuff.Mode.SRC_ATOP
        }

        kills.text = "${match.kills}/"
        assists.text = "/${match.assists}"
        deaths.text = match.deaths

        kp.text = "K/P ${match.killParticipation}%"
        duration.text = match.matchDuration
        type.text = match.matchType

        spree.text = when(match.largestSpree){
            2 -> "Double Kill"
            3 -> "Triple Kill"
            4 -> "Quadra Kill"
            5 -> "PENTAKILL"
            else -> {
                spree.visibility = View.INVISIBLE
                ""
            }
        }

        match.onChampionIconDownload{
            championIcon.setImageBitmap(match.championIcon)
            championIcon.visibility = View.VISIBLE
        }

        match.onItemsIconsDownload{
            for(i in 0 until match.itemsIcons.size){
                //var newBitmap:Bitmap? = null
                try {
                    (itemsIcons[i] as ShapeableImageView).setImageBitmap(match.itemsIcons[i])
                    (itemsIcons[i] as ShapeableImageView).visibility = View.VISIBLE
                } catch (e:Exception){

                }

            }
        }

        match.onRunesIconsDownload{
            for(i in 0 until 2){
                runes[i].setImageBitmap(match.runesIcons[i])
                runes[i].visibility = View.VISIBLE
            }
        }

        match.onSpellsIconsDownload{
            for(i in 0 until 2){
                spells[i].setImageBitmap(match.spellsIcons[i])
                spells[i].visibility = View.VISIBLE
            }
        }

    }
}