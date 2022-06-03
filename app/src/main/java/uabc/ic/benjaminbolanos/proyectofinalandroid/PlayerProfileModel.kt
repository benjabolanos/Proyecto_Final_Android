package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Match
import uabc.ic.benjaminbolanos.proyectofinalandroid.match_preview.MatchPreview
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Summoner

class PlayerProfileModel(private val summonerName: String, private val context: Context) {

    //Cuenta del jugador de este perfil
    lateinit var summoner: Summoner

    lateinit var summonerIcon: Bitmap

    private var matchIdList : ArrayList<String> = ArrayList()
    private val matchList   : ArrayList<Match>  = ArrayList()
    val matchPreviews = ArrayList<MatchPreview>(10)

    //APIs
    private val riotAPI : RiotAPI    = RiotAPI()
    private val ddApi   : DDragonAPI = DDragonAPI()

    //Contador de cuantos previews han sido creados
    private var matchPreviewCount = 0

    //Request queue
    private val queue = RequestsSingleton.getInstance(context).requestQueue

    //Evento que se ejecutará al terminar de cargar los datos del perfil
    private lateinit var onFinishListener:Callback

    fun createProfile(onFinishListener: Callback){
        getSummoner()
        //onFinishListener.onSuccess()
        this.onFinishListener = onFinishListener
    }

    private fun getSummoner(){
        val request = riotAPI.summonerRequest(
            { item ->
                summoner = item
                getMatchIdList()
            },
            summonerName)
        queue.add(request)
    }

    private fun getMatchIdList(){
        val request = riotAPI.matchListRequest({
            matchIdList = it
            Log.i("profile", "id list size: ${matchIdList.size}")
            getMatchList()
            },
                summoner.puuid
            )
        queue.add(request)
    }

    private fun getMatchList(){
        for(matchId in matchIdList){
            addMatchToList(matchId)
        }
    }

    private fun addMatchToList(matchID: String?){
        val id = matchPreviewCount
        matchPreviewCount++
        val request = riotAPI.matchRequest(
            { match ->
                matchList.add(match)
                match.metadata?.matchId?.let { Log.i("profile", it) }
                matchPreviews.add(MatchPreview(id,match,summoner, context))
                if(matchPreviews.size == 10){
                    sortMatchPreviewList()
                    onFinishListener.onSuccess()
                }
            },matchID)
        queue.add(request)
    }

    private fun sortMatchPreviewList(){
        for(i in 0 until matchPreviews.size){
            if(i != matchPreviews[i].id){
                val aux1 = matchPreviews[matchPreviews[i].id]
                val aux2 = matchPreviews[i]

                matchPreviews[i] = aux1
                matchPreviews[aux2.id] = aux2
            }
        }
    }

    fun onSummonerIconDownload(listener: Callback){
        val request = ddApi.profileIconRequest(summoner.profileIconId){
            summonerIcon = it
            listener.onSuccess()
        }
        queue.add(request)
    }
}
