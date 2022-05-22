package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.annotation.SuppressLint
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.Volley

class PlayerProfile : AppCompatActivity() {
    lateinit var playerProfileSummonerIcon: ImageView
    lateinit var playerProfileSummonerName: TextView
    lateinit var playerProfileMatchChampionIcon: ImageView
    lateinit var playerProfileMatchType: TextView
    lateinit var playerProfileMatchLength: TextView
    lateinit var playerProfileMatchKda: TextView
    lateinit var playerProfileSummonerLevel: TextView

    lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_profile)
        queue = Volley.newRequestQueue(this)
        initViews()
        mostrarInfo()
    }

    private fun initViews(){
        playerProfileSummonerIcon = findViewById(R.id.player_profile_summoner_icon)
        playerProfileSummonerName = findViewById(R.id.player_profile_summoner_name)
        playerProfileMatchChampionIcon = findViewById(R.id.player_profile_match_champion_icon)
        playerProfileMatchType = findViewById(R.id.player_profile_match_type)
        playerProfileMatchLength = findViewById(R.id.player_profile_match_length)
        playerProfileMatchKda = findViewById(R.id.player_profile_match_kda)
        playerProfileSummonerLevel = findViewById(R.id.player_profile_summoner_level)
    }

    @SuppressLint("SetTextI18n")
    private fun mostrarInfo(){
        var summonerIconID = 0; var summonerName = ""
        var summonerLevel:Long = 0
        var matchType = ""; var matchLength:Long = 0
        var playerKills:Int = 0; var playerDeaths:Int = 0
        var playerAssists:Int = 0; var championName = ""
        var data = intent.extras
        if(data != null){
            summonerIconID = data.get("summonerIconID") as Int
            summonerName = data.get("summonerName") as String
            summonerLevel = data.get("summonerLevel") as Long
            matchType = data.get("matchType") as String
            matchLength = data.get("gameLength") as Long
            playerKills = data.get("playerKills") as Int
            playerDeaths = data.get("playerDeaths") as Int
            playerAssists = data.get("playerAssists") as Int
            championName = data.get("championName") as String


            playerProfileSummonerName.text = summonerName
            playerProfileSummonerLevel.text = "Nivel: $summonerLevel"
            playerProfileMatchType.text = "Tipo de Juego: $matchType"
            playerProfileMatchLength.text = "${(matchLength/60).toInt()}:${matchLength%60}"
            playerProfileMatchKda.text = "$playerKills/$playerDeaths/$playerAssists"

        }
        setProfileIcon(summonerIconID)
        setChampionIcon(championName)
    }

    private fun setProfileIcon(summonerIconID:Int){
        val imageRequest = ImageRequest(
            "https://ddragon.leagueoflegends.com/cdn/12.9.1/img/profileicon/$summonerIconID.png",
            {bitmap ->
                playerProfileSummonerIcon.setImageBitmap(bitmap)
            },
            0, 0,
            ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,
            null)
        queue.add(imageRequest)
    }

    private fun setChampionIcon(championName:String){
        val championIconRequest = ImageRequest(
            "http://ddragon.leagueoflegends.com/cdn/12.9.1/img/champion/$championName.png",
            {bitmap ->
                playerProfileMatchChampionIcon.setImageBitmap(bitmap)
            },
            0,0,
            ImageView.ScaleType.CENTER_CROP,
            Bitmap.Config.ARGB_8888,
            null
        )
        queue.add(championIconRequest)
    }

}