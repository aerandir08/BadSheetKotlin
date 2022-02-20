package de.malte.badsheet

import android.content.Context
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import de.malte.badsheet.classes.HOME_AWAY
import de.malte.badsheet.classes.Match
import de.malte.badsheet.classes.apiClient
import de.malte.badsheet.databinding.ActivityMatchSettingsBinding
import de.malte.badsheet.utility.SharedPref


class MatchActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMatchSettingsBinding
    private var teamnames: Array<String?> = arrayOf()
    private var match = Match()
    private val sharedPref = SharedPref()
    private val client = apiClient()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        match = sharedPref.GetMatch(this)
        teamnames = sharedPref.loadArray("TEAMNAMES", this)

        // Set AutoCompleteTextView for teamnames
        if(teamnames.isEmpty())
        {
            teamnames = client.get_teamnames().toTypedArray()
        }
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, teamnames
        )
        binding.editHometeam.setAdapter(adapter)
        binding.editAwayteam.setAdapter(adapter)

        title = getString(R.string.match_settings)
        setMatchSettings()
    }

    /** Save Match to sharedPreferences **/
    override fun onPause()
    {
        super.onPause()
        getMatchSettings()
        sharedPref.SaveSharedPreference(this, match, "MATCH")
        sharedPref.saveArray(teamnames, "TEAMNAMES", this)
    }

    /** Get Match Settings from View **/
    fun getMatchSettings()
    {
        var players: Array<String?>
        val teamA = binding.editHometeam.text.toString()
        if(match.TeamA.Name != teamA)
        {
            players = client.get_players(teamA).toTypedArray()
            sharedPref.saveArray(players, HOME_AWAY.HOME.toString(), this)

            match.TeamA.Name = teamA
            match.TeamA.HomeAway = HOME_AWAY.HOME
        }

        val teamB = binding.editAwayteam.text.toString()
        if(match.TeamB.Name != teamB)
        {
            players = client.get_players(teamB).toTypedArray()
            sharedPref.saveArray(players, HOME_AWAY.AWAY.toString(), this)
            match.TeamB.Name = teamB
            match.TeamB.HomeAway = HOME_AWAY.AWAY
        }

        match.Location = binding.editLocation.text.toString()
        match.Group = binding.editGroup.text.toString()
        match.Time = binding.editTime.text.toString()
    }

    /** Set Match Settings to View **/
    fun setMatchSettings()
    {
        binding.editHometeam.setText(match.TeamA.Name)
        binding.editAwayteam.setText(match.TeamB.Name)
        binding.editLocation.setText(match.Location)
        binding.editGroup.setText(match.Group)
        binding.editTime.setText(match.Time)
    }
}