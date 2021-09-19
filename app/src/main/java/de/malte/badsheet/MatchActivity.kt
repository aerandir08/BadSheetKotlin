package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import de.malte.badsheet.classes.Match
import de.malte.badsheet.databinding.ActivityMainBinding
import de.malte.badsheet.databinding.ActivityMatchSettingsBinding
import de.malte.badsheet.utility.SharedPref

class MatchActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityMatchSettingsBinding
    private var match = Match()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchSettingsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
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
        match.TeamA.Name = binding.editHometeam.text.toString()
        match.TeamB.Name = binding.editAwayteam.text.toString()
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