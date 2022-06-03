package uabc.ic.benjaminbolanos.proyectofinalandroid.top_players

import android.content.Context
import uabc.ic.benjaminbolanos.proyectofinalandroid.Callback
import uabc.ic.benjaminbolanos.proyectofinalandroid.DDragonAPI
import uabc.ic.benjaminbolanos.proyectofinalandroid.RequestsSingleton
import uabc.ic.benjaminbolanos.proyectofinalandroid.RiotAPI
import java.util.*
import kotlin.collections.ArrayList

class TopPlayersModel(private val context: Context) {

    var topPlayersList: ArrayList<TopPlayer> = ArrayList()

    lateinit var challengerEntries : ArrayList<ChallengerEntry>

    private val riotApi : RiotAPI    = RiotAPI()

    private val queue = RequestsSingleton.getInstance(context).requestQueue

    private lateinit var listener:Callback

    fun onPlayersCreation(listener: Callback){
        getChallengerEntries()
        this.listener = listener
    }

    private fun getChallengerEntries(){
        val request = riotApi.topPlayersRequest{
            challengerEntries = it
            sortEntries()
            createTopPlayersList()
        }
        queue.add(request)
    }

    private fun createTopPlayersList(){
        for(i in 0 until 10){
            createTopPlayer(challengerEntries[i])
        }
    }

    private fun createTopPlayer(challengerEntry: ChallengerEntry){
        topPlayersList.add(TopPlayer(challengerEntry,context){
            if(topPlayersList.size == 10) listener.onSuccess()
        })
    }

    private fun sortEntries(){
        challengerEntries.sort()
    }
}