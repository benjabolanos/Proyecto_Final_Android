package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.ImageRequest
import com.android.volley.toolbox.JsonObjectRequest
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
        queue = Volley.newRequestQueue(this)
        playerNameText = findViewById(R.id.player_name_text)
        playerSearchView = findViewById(R.id.player_search)
        playerSearchButton = findViewById(R.id.player_search_button)
        playerSearchButton.setOnClickListener {
            val summonerName:String = playerSearchView.text.toString()
            //playerNameText.text = summonerName
            searchPlayer(summonerName)
        }
        getArray()
    }

    private fun searchPlayer(summonerName:String){

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
                headers["X-Riot-Token"] = "RGAPI-c96ebbd1-05e2-448e-88ea-4dc33668a860"
                return headers
            }
        }
        queue.add(stringRequst)
    }

    private fun getArray(){
        Log.i("cadena", "Get array")
        val gameListURL = "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/BNWii2rE7g54BV8c7KPYxwGdYvYXfCMuPy_I1_cWl7G4filqaJEghPRLpng1vwAB_bhEW8bRlxHjgw/ids?start=0&count=10"
        val jsonRequest = object: JsonObjectRequest(Request.Method.GET, gameListURL, null, Response.Listener { response ->
                try {
                    val jsonArray = response.getJSONArray("")
                    for( i in 0 until jsonArray.length()){
                        val gameID = jsonArray.getJSONObject(i)
                        Log.i("cadena", gameID.toString())
                    }
                }catch (e:Exception){
                    Log.i("cadena", "Error al abrir json object")
                }

        }, Response.ErrorListener { error ->
            error.printStackTrace()
        })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Riot-Token"] = "RGAPI-c96ebbd1-05e2-448e-88ea-4dc33668a860"
                return headers
            }
        }
        queue.add(jsonRequest)
    }

    private fun getIcon(iconID:Int){
        val iconView = findViewById<ImageView>(R.id.player_icon)
        val imageRequest = ImageRequest("$iconURL$iconID.png", {bitmap ->
            iconView.setImageBitmap(bitmap)
        }, 0, 0, ImageView.ScaleType.CENTER_CROP, Bitmap.Config.ARGB_8888,
            { _ -> })
        queue.add(imageRequest)


        /**
         * [
        "LA1_1250871565",
        "LA1_1247354652",
        "LA1_1247344758",
        "LA1_1246911582",
        "LA1_1246899273",
        "LA1_1246527875",
        "LA1_1245250024",
        "LA1_1245206410",
        "LA1_1245191985",
        "LA1_1244501218"
        ]

        Contador de request en una busqueda con riot api
            summoner: 1
            lista de n partidas: 1
            buscar las partidas: n

        imagenes cuentan con otro contador ya que son de ddragon


         */
    }

}