package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var playerNameText:TextView
    lateinit var playerSearchView: EditText
    lateinit var playerSearchButton: Button

    private lateinit var summoner: Summoner

    private lateinit var queue:RequestQueue
    private val summonerURL = "https://la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"
    private var iconURL = "https://ddragon.leagueoflegends.com/cdn/12.9.1/img/profileicon/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //searchPlayer()
        playerNameText = findViewById(R.id.player_name_text)
        playerSearchView = findViewById(R.id.player_search)
        playerSearchButton = findViewById(R.id.player_search_button)
        playerSearchButton.setOnClickListener {
            val summonerName:String = playerSearchView.text.toString()
            //playerNameText.text = summonerName
            searchPlayer(summonerName)
        }
    }

    private fun searchPlayer(summonerName:String){

        queue = Volley.newRequestQueue(this)

        val stringRequst = object: StringRequest(Request.Method.GET, summonerURL+summonerName,
            Response.Listener { response ->
                response.trimIndent()
                val gson = Gson()
                summoner = gson.fromJson(response, Summoner::class.java)
                playerNameText.text = summoner.name
                summoner.profileIconId?.let { getIcon(it) }
            },
            Response.ErrorListener {  })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Riot-Token"] = "RGAPI-608bc761-a9a4-4d35-aaed-6368f008041b"
                return headers
            }
        }
        queue.add(stringRequst)
    }

    private fun getIcon(iconID:Int){
        val iconView = findViewById<ImageView>(R.id.player_icon)
        val imageRequest = ImageRequest("$iconURL$iconID.png", {bitmap ->
            iconView.setImageBitmap(bitmap)
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,
            { _ -> })
        queue.add(imageRequest)
    }

}