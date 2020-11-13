package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.core.view.children
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_team.*

class TeamActivity : AppCompatActivity() {

    private var match = Match()
    private var team = Team("")
    private var teamname = ""

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team)

        teamname = intent.getStringExtra("TEAM")!!
        match = Util().GetMatch(this)
        team = match.get_team(teamname)

        title = team.Name
        set_player_names()
    }

    /** Save Team to sharedPreferences **/
    override fun onPause()
    {
        super.onPause()
        get_player_names()
        match.set_team(teamname, team)
        Util().SavePreferences(this, match, "MATCH")
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