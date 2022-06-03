package uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.grid_view

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import uabc.ic.benjaminbolanos.proyectofinalandroid.R
import uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.TopPlayer

class TopPlayerHolder (view : View): RecyclerView.ViewHolder(view){

    val summonerIcon = view.findViewById<ShapeableImageView>(R.id.top_player_item_icon)
    val summonerName = view.findViewById<TextView>(R.id.top_player_item_name)
    val summonerLevel = view.findViewById<TextView>(R.id.top_player_item_level)

    fun render(player: TopPlayer, onClickListener:(TopPlayer) -> Unit){

        summonerIcon.apply {
            player.onPlayerIconDownload{
                setImageBitmap(player.summonerIcon)
            }
        }

        summonerName.apply {
            text = player.summoner.name
        }

        summonerLevel.apply {
            text = player.summoner.summonerLevel.toString()
        }

        itemView.setOnLongClickListener {
            onClickListener(player)
            true
        }

    }
}