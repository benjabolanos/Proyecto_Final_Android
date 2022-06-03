package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.getbase.floatingactionbutton.FloatingActionButton
import com.getbase.floatingactionbutton.FloatingActionsMenu
import com.google.android.material.button.MaterialButton
import com.google.android.material.imageview.ShapeableImageView
import uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.TopPlayer
import uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.TopPlayersModel
import uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.grid_view.TopPlayerAdapter

class TopPlayers : AppCompatActivity() {

    lateinit var topPlayersModel: TopPlayersModel
    lateinit var recyclerView: RecyclerView
    lateinit var linearLayout:LinearLayoutManager
    lateinit var gridLayout: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_players)
        createTopPlayersView()
    }

    fun createTopPlayersView(){
        topPlayersModel = TopPlayersModel(this)
        topPlayersModel.onPlayersCreation{
            initRecyclerView()
        }
    }

    fun initRecyclerView(){
        initFabListeners()

        gridLayout = GridLayoutManager(this, 2)
        linearLayout = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.top_players_recycler_view)
        recyclerView.layoutManager = gridLayout
        recyclerView.adapter = TopPlayerAdapter(topPlayersModel.topPlayersList){
            player -> showPlayerDialog(player)
        }

        findViewById<ProgressBar>(R.id.top_players_progressbar).apply {
            visibility = View.INVISIBLE
        }

        findViewById<FloatingActionsMenu>(R.id.top_players_fab_menu).apply {
            visibility = View.VISIBLE
        }
    }

    fun initFabListeners(){
        val fabGrid = findViewById<FloatingActionButton>(R.id.top_player_fab_grid)
        val fabLinear = findViewById<FloatingActionButton>(R.id.top_player_fab_linear)

        fabGrid.setOnClickListener {
            recyclerView.layoutManager = gridLayout

        }

        fabLinear.setOnClickListener {
            recyclerView.layoutManager = linearLayout
        }
    }

    fun showPlayerDialog(player: TopPlayer) {

        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_top_player)

        dialog.window?.findViewById<ShapeableImageView>(R.id.dialog_player_icon)?.apply {
            setImageBitmap(player.summonerIcon)
        }

        dialog.window?.findViewById<TextView>(R.id.dialog_player_name)?.apply {
            text = player.summoner.name
        }

        dialog.window?.findViewById<TextView>(R.id.dialog_player_level)?.apply {
            text = player.summoner.summonerLevel.toString()
        }

        dialog.window?.findViewById<MaterialButton>(R.id.dialog_player_button)?.setOnClickListener {
            val intent = Intent(this, PlayerProfile::class.java)
            intent.putExtra("SummonerName", player.summoner.name)
            startActivity(intent)
        }

        dialog.show()
    }

}