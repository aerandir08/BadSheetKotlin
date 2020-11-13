package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.core.view.children
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {

    private var team = Team("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        val teamname = intent.getStringExtra("TEAM")!!
        team = Team(teamname)

        var json_team = Util().LoadPreferences(this, team.Name)
        if (json_team != "")
        {
            var gson: Gson = Gson()
            team = gson.fromJson(json_team, Team::class.java)
        }
        set_player_names()
    }

    /** Save Team to sharedPreferences **/
    override fun onPause() {
        super.onPause()
        get_player_names()
        Util().SavePreferences(this, team, team.Name)
    }

    /** Get Player Names from View **/
    fun get_player_names()
    {
        var layout: LinearLayout = layout_linearLayout
        var idx: Int = 0
        for (child in layout.children)
        {
            if (child is AutoCompleteTextView)
            {
                team.Player[idx] = child.text.toString()
                idx += 1
            }
        }
    }

    /** Set Player Names to View **/
    fun set_player_names()
    {
        var layout: LinearLayout = layout_linearLayout
        var idx: Int = 0
        for (child in layout.children)
        {
            if (child is AutoCompleteTextView)
            {
                child.setText(team.Player[idx])
                    idx += 1
            }
        }
    }
}