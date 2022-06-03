package uabc.ic.benjaminbolanos.proyectofinalandroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.android.volley.RequestQueue
import com.android.volley.toolbox.*
import com.google.android.material.textfield.TextInputEditText
import uabc.ic.benjaminbolanos.proyectofinalandroid.pojo.Summoner

class MainActivity : AppCompatActivity() {

    lateinit var playerSearchButton: Button
    lateinit var playerSearch : TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerSearchButton = findViewById(R.id.player_search_button)
        playerSearch = findViewById(R.id.main_menu_input)

        playerSearchButton.setOnClickListener {
            val summonerName:String = playerSearch.text.toString()
            searchPlayer(summonerName)
        }

    }

    private fun searchPlayer(summonerName:String){
        val name = summonerName.trim()
        if(name == "") return

        val intent = Intent(this, PlayerProfile::class.java)
        intent.putExtra("SummonerName", name)
        startActivity(intent)
    }

    fun goToTopPlayers(view: View){
        val intent = Intent(this, TopPlayers::class.java)
        startActivity(intent)
    }
}