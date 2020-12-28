package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.core.view.children
import de.malte.badsheet.classes.Match
import de.malte.badsheet.classes.Team
import de.malte.badsheet.utility.SharedPref
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
        match = SharedPref().GetMatch(this)
        team = match.getTeam(teamname)

        title = team.Name
        setPlayerNames()
    }

    /** Save Team to sharedPreferences **/
    override fun onPause()
    {
        super.onPause()
        getPlayerNames()
        match.setTeam(teamname, team)
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }

    /** Get Player Names from View **/
    fun getPlayerNames()
    {
        val layout: LinearLayout = layout_linearLayout
        var idx = 0
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
    fun setPlayerNames()
    {
        val layout: LinearLayout = layout_linearLayout
        var idx = 0
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