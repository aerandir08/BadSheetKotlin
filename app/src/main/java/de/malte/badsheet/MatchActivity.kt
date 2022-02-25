package de.malte.badsheet

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import de.malte.badsheet.classes.HOME_AWAY
import de.malte.badsheet.classes.Match
import de.malte.badsheet.classes.ApiClient
import de.malte.badsheet.databinding.ActivityMatchSettingsBinding
import de.malte.badsheet.utility.SharedPref


class MatchActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMatchSettingsBinding
    private var teamnames: Array<String?> = arrayOf()
    private var match = Match()
    private val sharedPref = SharedPref()
    private val client = ApiClient()

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
            teamnames = client.getTeamnames().toTypedArray()
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
    private fun getMatchSettings()
    {
        var players: Array<String?>
        val teamA = binding.editHometeam.text.toString()
        if(match.teamA.Name != teamA)
        {
            players = client.getPlayers(teamA).toTypedArray()
            sharedPref.saveArray(players, HOME_AWAY.HOME.toString(), this)

            match.teamA.Name = teamA
            match.teamA.homeAway = HOME_AWAY.HOME
        }

        val teamB = binding.editAwayteam.text.toString()
        if(match.teamB.Name != teamB)
        {
            players = client.getPlayers(teamB).toTypedArray()
            sharedPref.saveArray(players, HOME_AWAY.AWAY.toString(), this)
            match.teamB.Name = teamB
            match.teamB.homeAway = HOME_AWAY.AWAY
        }

        match.location = binding.editLocation.text.toString()
        match.group = binding.editGroup.text.toString()
        match.time = binding.editTime.text.toString()
    }

    /** Set Match Settings to View **/
    private fun setMatchSettings()
    {
        binding.editHometeam.setText(match.teamA.Name)
        binding.editAwayteam.setText(match.teamB.Name)
        binding.editLocation.setText(match.location)
        binding.editGroup.setText(match.group)
        binding.editTime.setText(match.time)
    }
}