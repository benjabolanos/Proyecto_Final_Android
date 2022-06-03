package uabc.ic.benjaminbolanos.proyectofinalandroid.match_preview

import android.content.Context
import android.graphics.Bitmap
import uabc.ic.benjaminbolanos.proyectofinalandroid.DDragonAPI
import uabc.ic.benjaminbolanos.proyectofinalandroid.RequestsSingleton
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Match
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Participant
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Summoner

/**
 * Esta clase contiene los datos e imagenes necesarias para mostrar el preview de una
 * partida en la vista inicial del perfil del jugador.
 */
class MatchPreview(val id: Int, val match: Match, summoner: Summoner, context: Context) {

    fun interface Listener{
        fun onSuccess()
    }

    //Api connection
    private val queue = RequestsSingleton.getInstance(context).requestQueue
    private val ddAPI = DDragonAPI()

    //Items, runes and spells icons
    val itemsIcons = ArrayList<Bitmap>(6)
    val runesIcons = ArrayList<Bitmap>(2)
    val spellsIcons = ArrayList<Bitmap>(2)

    //Champion icon
    lateinit var championIcon: Bitmap

    //Kills stats
    var largestSpree = 0
    var killParticipation = 0

    //KDA
    lateinit var kills: String
    lateinit var assists: String
    lateinit var deaths: String

    //Match data
    lateinit var matchDuration: String
    lateinit var matchType: String

    //Match won/lost
    var matchWon = false

    private val positionInParticipants: Int? = summoner.puuid?.let { getPosition(it) }
    val summonerParticipant: Participant? =
        positionInParticipants?.let { match.info?.participants?.get(it) }

    private var itemsComplete = false
    private var runesComplete = false
    private var spellsComplete = false
    private var championComplete = false


    init {
        setMatchInfo()
    }

    private fun getPosition(summonerUUID: String): Int {

        for ((i, participant) in match.metadata?.participants!!.withIndex()) {
            if (participant == summonerUUID) {
                return i
            }
        }
        return -1
    }

    fun onItemsIconsDownload(listener: Listener) {
        if(itemsComplete){
            listener.onSuccess()
            return
        }

        val itemIdList = summonerParticipant?.let { p ->
            arrayListOf(
                p.item0, p.item1,
                p.item2, p.item3, p.item4, p.item5
            )
        }

        if (itemIdList != null) {
            for (itemId in itemIdList) {
                val request = ddAPI.itemIconRequest(itemId) { bitmap ->
                    itemsIcons.add(bitmap)
                    itemsComplete = true
                    listener.onSuccess()
                }
                queue.add(request)
            }
        }
    }

    fun onRunesIconsDownload(listener: Listener) {
        if(runesComplete){
            listener.onSuccess()
            return
        }

        val styles = summonerParticipant?.perks?.styles

        val request = ddAPI.runeIconRequest(styles!![0].selections[0].perk) { ic1 ->
            runesIcons.add(ic1)
            val req = ddAPI.runeIconRequest(styles[1].style) { ic2 ->
                runesIcons.add(ic2)
                runesComplete = true
                listener.onSuccess()
            }
            queue.add(req)
        }
        queue.add(request)
    }

    fun onSpellsIconsDownload(listener: Listener) {
        if(spellsComplete){
            listener.onSuccess()
            return
        }

        val request = ddAPI.summonerSpellIconRequest(summonerParticipant?.summoner1Id) { ic1 ->
            spellsIcons.add(ic1)
            val req = ddAPI.summonerSpellIconRequest(summonerParticipant?.summoner2Id) { ic2 ->
                spellsIcons.add(ic2)
                spellsComplete = true
                listener.onSuccess()
            }
            queue.add(req)
        }
        queue.add(request)
    }

    fun onChampionIconDownload(listener: Listener) {
        if(championComplete){
            listener.onSuccess()
            return
        }

        summonerParticipant?.championName?.let {
            val request = ddAPI.championIconRequest(it) { icon ->
                championIcon = icon
                championComplete = true
                listener.onSuccess()
            }
            queue.add(request)
        }
    }

    private fun setMatchInfo() {
        val mins = match.info?.gameDuration?.div(60)
        val secs = match.info?.gameDuration?.rem(60)
        if (secs != null) {
            matchDuration = if(secs < 10) "$mins:0$secs"
            else "$mins:$secs"
        }

        matchType = match.info?.gameMode.toString()

        kills = summonerParticipant?.kills.toString()
        assists = summonerParticipant?.assists.toString()
        deaths = summonerParticipant?.deaths.toString()

        val team = match.info?.teams?.get((summonerParticipant?.teamId?.div(100))?.minus(1)!!)
        largestSpree = summonerParticipant?.largestMultiKill!!
        killParticipation = if(team?.objectives?.champion?.kills!! == 0){
            0
        } else {
            ((summonerParticipant.kills!! + summonerParticipant.assists!!) *100)/ team?.objectives?.champion?.kills!!
        }

        matchWon = team.win == true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MatchPreview

        if (match != other.match) return false
        if (summonerParticipant != other.summonerParticipant) return false

        return true
    }

    override fun hashCode(): Int {
        var result = match.hashCode()
        result = 31 * result + (summonerParticipant?.hashCode() ?: 0)
        return result
    }
}