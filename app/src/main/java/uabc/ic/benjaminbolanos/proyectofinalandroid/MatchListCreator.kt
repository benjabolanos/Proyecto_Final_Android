package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import uabc.ic.benjaminbolanos.proyectofinalandroid.api.Match
import uabc.ic.benjaminbolanos.proyectofinalandroid.api.ext
import java.lang.Exception

class MatchListCreator(private val playerUUID: String, val context: Context) {

    private val gameListURL:String = "https://americas.api.riotgames.com/lol/match/v5" +
            "/matches/by-puuid/$playerUUID/ids?start=0&count=10"

    private var gameIdList: ArrayList<String> = ArrayList(10)

    private val gameIdListLiveData = gameIdList

    val queue: RequestQueue = Volley.newRequestQueue(context)

    init {
        getGameIDList()
    }

    fun getGameIDList(){
        val arrayRequest = object: JsonArrayRequest(Method.GET, gameListURL, null,
        Response.Listener {
            try {
                for(i in 0 until it.length()){
                    gameIdList.add(it[i] as String)
                    getMatchInfo(it[i] as String)
                }
            } catch (e: Exception){
                e.printStackTrace()
            }
        }, Response.ErrorListener { error ->
                error.printStackTrace()
            })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Riot-Token"] = "RGAPI-83f6d7db-b986-4236-9814-af48b9705bbe"
                return headers
            }
        }
        queue.add(arrayRequest)
    }

    fun getMatchInfo(gameID:String){
        val gameURL = "https://americas.api.riotgames.com/lol/match/v5/matches/$gameID"
        val matchRequest = object: StringRequest(Method.GET, gameURL,
        Response.Listener { response ->
            response.trimIndent()
            val gson = Gson()
            val match = gson.fromJson(response, Match::class.java)
            ext.matchList.add(match)
        }, Response.ErrorListener { error ->
                error.printStackTrace()
        })
        {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["X-Riot-Token"] = "RGAPI-83f6d7db-b986-4236-9814-af48b9705bbe"
                return headers
            }
        }
        queue.add(matchRequest)
    }

}