package uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.grid_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.proyectofinalandroid.R
import uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.TopPlayer

class TopPlayerAdapter(val topPlayersList: List<TopPlayer>,
                       private val onClickListener:(TopPlayer) -> Unit) : RecyclerView.Adapter<TopPlayerHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopPlayerHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TopPlayerHolder(layoutInflater.inflate(R.layout.top_player_item, parent, false))
    }

    override fun onBindViewHolder(holder: TopPlayerHolder, position: Int) {
        val item = topPlayersList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = topPlayersList.size
}