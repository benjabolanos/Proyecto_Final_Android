package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.util.Log
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.google.gson.Gson
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.ChallengerLeague
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Match
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Summoner
import uabc.ic.benjaminbolanos.proyectofinalandroid.top_players.ChallengerEntry

class RiotAPI {

    fun interface Request<T> {
        fun onSuccess(item: T)
    }

    fun getRiotToken(): MutableMap<String, String> {
        val headers = HashMap<String, String>()
        headers["X-Riot-Token"] = "RGAPI-cc2b9c73-6e6d-4926-87e9-dbd0f1a72b9a"
        return headers
    }

    /**
     * Request para obtener un Summoner a partir del SummonerName
     */
    fun summonerRequest(callback: Request<Summoner>, summonerName: String?): StringRequest {
        //URL para solicitar Summoner a partir de SummonerName
        val summonerURL =
            "https://la1.api.riotgames.com/lol/summoner/v4/summoners/by-name/$summonerName"

        //String Request del Summoner
        val summonerRequest = object : StringRequest(Method.GET, summonerURL,
            Response.Listener { response ->
                response.trimIndent()
                val gson = Gson()
                val summoner = gson.fromJson(response, Summoner::class.java)
                callback.onSuccess(summoner)
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> = getRiotToken()
        }

        return summonerRequest
    }

    fun matchRequest(callback: Request<Match>, matchID: String?): StringRequest {
        val matchURL = "https://americas.api.riotgames.com/lol/match/v5/matches/$matchID"
        Log.i("profile", "matchcreado dur: $matchID")
        val matchRequest = object : StringRequest(Method.GET, matchURL,
            Response.Listener { matchJson ->
                val gson = Gson()
                val match = gson.fromJson(matchJson, Match::class.java)
                callback.onSuccess(match)
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }) {
            override fun getHeaders(): MutableMap<String, String> = getRiotToken()
        }
        return matchRequest
    }

    fun matchListRequest(
        callback: Request<ArrayList<String>>,
        summonerPUUID: String?
    ): JsonArrayRequest {
        val matchListURL =
            "https://americas.api.riotgames.com/lol/match/v5/matches/by-puuid/$summonerPUUID/ids?start=0&count=10"

        val matchListRequest = object : JsonArrayRequest(Method.GET, matchListURL, null,
            Response.Listener { response ->
                val matchList = ArrayList<String>()
                try {
                    for (i in 0 until response.length()) {
                        Log.i("profile", "matchagregado: ${response[i] as String}")
                        matchList.add(response[i] as String)
                    }

                    callback.onSuccess(matchList)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            },
            Response.ErrorListener { error ->
                error.printStackTrace()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> = getRiotToken()
        }

        return matchListRequest
    }

    fun topPlayersRequest(callback: Request<ArrayList<ChallengerEntry>>): StringRequest {
        val topPlayersListURL = "https://la1.api.riotgames.com/lol/league/v4/" +
                "challengerleagues/by-queue/RANKED_SOLO_5x5"

        val topPlayersRequest = object : StringRequest(Method.GET,topPlayersListURL,
            Response.Listener { response ->
                val challengerEntries  = Gson().fromJson(response, ChallengerLeague::class.java)
                val topPlayers = challengerEntries.entries
                callback.onSuccess(topPlayers)
            },
            Response.ErrorListener {
                it.printStackTrace()
            }
        ) {
            override fun getHeaders(): MutableMap<String, String> = getRiotToken()
        }

        return topPlayersRequest
    }

}