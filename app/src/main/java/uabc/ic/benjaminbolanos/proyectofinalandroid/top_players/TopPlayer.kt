package uabc.ic.benjaminbolanos.proyectofinalandroid.top_players

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import uabc.ic.benjaminbolanos.proyectofinalandroid.Callback
import uabc.ic.benjaminbolanos.proyectofinalandroid.DDragonAPI
import uabc.ic.benjaminbolanos.proyectofinalandroid.RequestsSingleton
import uabc.ic.benjaminbolanos.proyectofinalandroid.RiotAPI
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Summoner

class TopPlayer(val player: ChallengerEntry, context: Context, private val onFinishListener: Callback) {

    lateinit var summonerIcon : Bitmap
    var summoner     : Summoner = Summoner()

    private val ddAPI   : DDragonAPI = DDragonAPI()
    private val riotAPI : RiotAPI    = RiotAPI()

    private val queue = RequestsSingleton.getInstance(context).requestQueue

    init {
        getSummoner()
    }

    private fun getSummoner(){
        val request = riotAPI.summonerRequest({
            summoner = it
            Log.i("top_player", "summoner list ${summoner.name}")
            onFinishListener.onSuccess()
        },player.summonerName)
        queue.add(request)
    }

    fun onPlayerIconDownload(listener: Callback){
        val request = ddAPI.profileIconRequest(summoner.profileIconId){
            summonerIcon = it
            listener.onSuccess()
        }
        queue.add(request)
    }

}