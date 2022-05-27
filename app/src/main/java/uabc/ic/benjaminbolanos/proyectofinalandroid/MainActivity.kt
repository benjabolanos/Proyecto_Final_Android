package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.*
import com.google.android.material.imageview.ShapeableImageView
import com.google.gson.Gson
import com.google.gson.JsonArray
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var playerNameText:TextView
    lateinit var playerSearchView: EditText
    lateinit var playerSearchButton: Button

    private lateinit var summoner: Summoner

    private lateinit var queue:RequestQueue
    private val summonerURL = "https://la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/"
    private var iconURL = "https://ddragon.leagueoflegends.com/cdn/12.9.1/img/profileicon/"

    lateinit var profileIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //searchPlayer()
        queue = Volley.newRequestQueue(this)
        profileIntent = Intent(applicationContext, PlayerProfile::class.java)
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

        val stringRequst = object: StringRequest(Method.GET, summonerURL+summonerName,
            Response.Listener { response ->
                response.trimIndent()
                val gson = Gson()
                summoner = gson.fromJson(response, Summoner::class.java)
                playerNameText.text = summoner.name
                summoner.profileIconId?.let { getIcon(it) }
                summoner.puuid?.let { getGameID(it) }
                profileIntent.putExtra("summonerIconID", summoner.profileIconId)
                profileIntent.putExtra("summonerName", summoner.name)
                profileIntent.putExtra("summonerLevel", summoner.summonerLevel)
            },
            Response.ErrorListener {  })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Riot-Token"] = "RGAPI-300b9d39-fa24-45c2-a157-91d012029c1a"
                return headers
            }
        }
        queue.add(stringRequst)
    }

    private fun getGameID(playerID:String):String{
        var gameID = ""
        val gameListURL =
            "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/$playerID/ids?start=0&count=10"
        val jsonRequest = object: JsonArrayRequest(Method.GET, gameListURL, null, Response.Listener { response ->
                try {
                    gameID = response.get(0) as String
                    Log.i("cadena", "game id $gameID")
                    getGameInfo(gameID, playerID)
                }catch (e:Exception){
                    Log.i("cadena", "Error al abrir json object")
                    e.printStackTrace()
                }
        }, Response.ErrorListener { error ->
            error.printStackTrace()
        })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Riot-Token"] = "RGAPI-300b9d39-fa24-45c2-a157-91d012029c1a"
                return headers
            }
        }
        queue.add(jsonRequest)
        return gameID
    }

    private fun getGameInfo(gameID:String, playerID: String){
        if(gameID != "") {
            val gameURL = "https://americas.api.riotgames.com/lol/match/v5/matches/$gameID"
            val jsonRequest = object :
                JsonObjectRequest(Method.GET, gameURL, null, Response.Listener { response ->
                    val gameInfo = response.getJSONObject("info")
                    val gameMetaParticipants = response
                                            .getJSONObject("metadata")
                                            .getJSONArray("participants")
                    val gameInfoParticipant = gameInfo.getJSONArray("participants")
                    val gameMode = gameInfo.getString("gameMode")
                    val gameDuration = gameInfo.getLong("gameDuration")
                    val gameVersion = gameInfo.getString("gameVersion")
                    val gameInfoString = "Ultimo juego: \n Modo de juego: $gameMode\n" +
                            "Duracion en segundos: $gameDuration \n" +
                            "Version del juego: $gameVersion"
                    Log.i("cadena", gameInfoString)
                    profileIntent.putExtra("matchType",gameMode)
                    profileIntent.putExtra("gameLength", gameDuration)
                    findPlayerInfo(playerID, gameMetaParticipants, gameInfoParticipant)

                }, Response.ErrorListener { error ->
                    error.printStackTrace()
                }) {
                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers["X-Riot-Token"] = "RGAPI-300b9d39-fa24-45c2-a157-91d012029c1a"
                    return headers
                }
            }
            queue.add(jsonRequest)
        } else {
            Log.i("cadena", "Game ID vacio")
        }
    }

    private fun findPlayerInfo(playerID: String, participantsMeta: JSONArray, participantsInfo: JSONArray){
        var playerPos = -1
        for(i in 0 until participantsMeta.length()){
            if(playerID == participantsMeta.getString(i)) playerPos = i
        }
        if(playerPos == -1) Log.i("cadena","Error al buscar jugador")
        else {
            val playerInfo = participantsInfo.getJSONObject(playerPos)
            val playerKills = playerInfo.getInt("kills")
            val playerDeaths = playerInfo.getInt("deaths")
            val playerAssists = playerInfo.getInt("assists")
            val playerChampionName = playerInfo.getString("championName")
            profileIntent.putExtra("playerKills", playerKills)
            profileIntent.putExtra("playerDeaths", playerDeaths)
            profileIntent.putExtra("playerAssists", playerAssists)
            profileIntent.putExtra("championName", playerChampionName)
            startActivity(profileIntent)
        }
    }

    private fun getIcon(iconID:Int){
        val iconView = findViewById<ShapeableImageView>(R.id.player_icon)
        val imageRequest = ImageRequest("$iconURL$iconID.png", {bitmap ->
            iconView.setImageBitmap(bitmap)
            //iconView.
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