package de.malte.badsheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    /** Called when Button Match Settings is clicked. **/
    fun open_match_settings(view: View)
    {
        val intent = Intent(this, MatchActivity::class.java).apply {}
        startActivity(intent)
    }

    /** Called when a Team is clicked. **/
    fun open_team(team: String)
    {
        var match = Util().GetMatch(this)
        var intent = Intent()
        if(team == "home")
        {
            intent = Intent(this, TeamActivity::class.java).apply {putExtra("TEAM", match.TeamA)}
        }
        else
        {
            intent = Intent(this, TeamActivity::class.java).apply {putExtra("TEAM", match.TeamB)}
        }
        startActivity(intent)
    }

    /** Called when TeamA is clicked **/
    fun open_teamA(view: View)
    {
        open_team("home")
    }

    /** Called when TeamB is clicked **/
    fun open_teamB(view: View)
    {
        open_team("away")
    }

    /** Called when Button Scoreboard is clicked. **/
    fun open_scoreboard(view: View)
    {
        val intent = Intent(this, ScoreboardActivity::class.java).apply {}
        startActivity(intent)
    }
}