package uabc.ic.benjaminbolanos.proyectofinalandroid.match_preview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uabc.ic.benjaminbolanos.proyectofinalandroid.R

class MatchPreviewAdapter(val matchPreviewList:List<MatchPreview>) : RecyclerView.Adapter<MatchPreviewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchPreviewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MatchPreviewHolder(layoutInflater.inflate(R.layout.match_item, parent, false))
    }

    override fun onBindViewHolder(holder: MatchPreviewHolder, position: Int) {
        val item = matchPreviewList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = matchPreviewList.size
}