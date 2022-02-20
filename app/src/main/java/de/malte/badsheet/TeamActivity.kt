package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.appcompat.R
import androidx.core.view.children
import de.malte.badsheet.classes.Match
import de.malte.badsheet.classes.Team
import de.malte.badsheet.classes.apiClient
import de.malte.badsheet.databinding.ActivityTeamBinding
import de.malte.badsheet.utility.SharedPref

class TeamActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTeamBinding
    private var match = Match()
    private var team = Team("")
    private var teamname = ""
    private val sharedPref = SharedPref()
    private var players: Array<String?> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        teamname = intent.getStringExtra("TEAM")!!
        match = sharedPref.GetMatch(this)
        team = match.getTeam(teamname)

        players = sharedPref.loadArray(team.HomeAway.toString(), this)

        // Set AutoCompleteTextView for teamnames
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.support_simple_spinner_dropdown_item, players
        )
        binding.editName0.setAdapter(adapter)
        binding.editName1.setAdapter(adapter)
        binding.editName2.setAdapter(adapter)
        binding.editName3.setAdapter(adapter)
        binding.editName4.setAdapter(adapter)
        binding.editName5.setAdapter(adapter)
        binding.editName6.setAdapter(adapter)
        binding.editName7.setAdapter(adapter)
        binding.editName8.setAdapter(adapter)
        binding.editName9.setAdapter(adapter)
        binding.editName10.setAdapter(adapter)
        binding.editName11.setAdapter(adapter)

        title = team.Name
        setPlayerNames()
    }

    /** Save Team to sharedPreferences **/
    override fun onPause()
    {
        super.onPause()
        getPlayerNames()
        match.setTeam(teamname, team)
        sharedPref.SaveSharedPreference(this, match, "MATCH")
    }

    /** Get Player Names from View **/
    fun getPlayerNames()
    {
        val layout: LinearLayout = binding.layoutLinearLayout
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
        val layout: LinearLayout = binding.layoutLinearLayout
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