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
        val intent = Intent(this, MatchSettings::class.java).apply {}
        startActivity(intent)
    }

    /** Called when a Team Button is clicked. **/
    fun open_team(view: View)
    {
        val intent = Intent(this, Team::class.java).apply {/*TODO: Hier muss das Klassenobjekt des jeweiligen Teams rein.*/}
        startActivity(intent)
    }

    /** Called when Button Scoreboard is clicked. **/
    fun open_scoreboard(view: View)
    {
        val intent = Intent(this, Scoreboard::class.java).apply {}
        startActivity(intent)
    }
}