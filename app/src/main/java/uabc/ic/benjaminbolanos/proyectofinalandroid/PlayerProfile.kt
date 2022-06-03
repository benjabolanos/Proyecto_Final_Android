package uabc.ic.benjaminbolanos.proyectofinalandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import uabc.ic.benjaminbolanos.proyectofinalandroid.match_preview.MatchPreviewAdapter

class PlayerProfile : AppCompatActivity() {

    lateinit var playerProfileModel: PlayerProfileModel
    lateinit var recyclerView: RecyclerView

    lateinit var progressBar: ProgressBar
    lateinit var summonerIcon: ShapeableImageView
    lateinit var summonerName: TextView
    lateinit var summonerLevel: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_profile)

        progressBar = findViewById(R.id.player_profile_progress)
        summonerIcon = findViewById(R.id.player_profile_summoner_icon)
        summonerName = findViewById(R.id.player_profile_summoner_name)
        summonerLevel = findViewById(R.id.player_profile_summoner_level)

        createPlayerProfile()
    }

    fun createPlayerProfile(){
        val data = intent.extras
        if(data != null){
            val summonerName = data.getString("SummonerName")
            playerProfileModel = summonerName?.let { PlayerProfileModel(it,this) }!!
            playerProfileModel.createProfile{
                initRecyclerView()
                setupSummonerInfo()
            }
        }
    }

    fun initRecyclerView(){
        recyclerView = findViewById(R.id.match_preview_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = MatchPreviewAdapter(playerProfileModel.matchPreviews)
        recyclerView.adapter = adapter
        progressBar.visibility = View.INVISIBLE
    }

    fun setupSummonerInfo(){
        playerProfileModel.onSummonerIconDownload{
            summonerIcon.apply {
                setImageBitmap(playerProfileModel.summonerIcon)
                visibility = View.VISIBLE
            }
        }
        summonerName.apply {
            text = playerProfileModel.summoner.name
            visibility = View.VISIBLE
        }
        summonerLevel.apply {
            text = playerProfileModel.summoner.summonerLevel.toString()
            visibility = View.VISIBLE
        }

        findViewById<ShapeableImageView>(R.id.player_profile_summoner_icon_frame).visibility = View.VISIBLE
    }
}