package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.malte.badsheet.classes.Match
import de.malte.badsheet.utility.SharedPref
import kotlinx.android.synthetic.main.activity_match_settings.*

class MatchActivity : AppCompatActivity()
{
    private var match = Match()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_settings)
        match = SharedPref().GetMatch(this)

        title = getString(R.string.match_settings)
        setMatchSettings()
    }

    /** Save Match to sharedPreferences **/
    override fun onPause()
    {
        super.onPause()
        getMatchSettings()
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }

    /** Get Match Settings from View **/
    fun getMatchSettings()
    {
        match.TeamA.Name = edit_hometeam.text.toString()
        match.TeamB.Name = edit_awayteam.text.toString()
        match.Location = edit_location.text.toString()
        match.Group = edit_group.text.toString()
        match.Time = edit_time.text.toString()
    }

    /** Set Match Settings to View **/
    fun setMatchSettings()
    {
        edit_hometeam.setText(match.TeamA.Name)
        edit_awayteam.setText(match.TeamB.Name)
        edit_location.setText(match.Location)
        edit_group.setText(match.Group)
        edit_time.setText(match.Time)
    }
}