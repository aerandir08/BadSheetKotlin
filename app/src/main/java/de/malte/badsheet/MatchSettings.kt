package de.malte.badsheet

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_match_settings.*

class MatchSettings : AppCompatActivity()
{
    private var match = Match()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match_settings)
        LoadPreferences()
        set_match_settings()
    }

    override fun onPause()
    {
        super.onPause()
        get_match_settings()
        SavePreferences()
    }

    fun SavePreferences()
    {
        var sharedPreferences: SharedPreferences = getPreferences(MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var gson: Gson = Gson()
        var json_match: String = gson.toJson(match)
        editor.putString("MATCH", json_match)
        editor.commit()
    }

    fun LoadPreferences()
    {
        var sharedpreferences: SharedPreferences = getPreferences(MODE_PRIVATE)
        var gson: Gson = Gson()
        var json_match: String? = sharedpreferences.getString("MATCH", "")
        if(json_match != "")
        {
            match = gson.fromJson(json_match, Match::class.java)
        }
        else
        {
            match = Match()
        }
    }

    fun get_match_settings()
    {
        match.TeamA = edit_hometeam.text.toString()
        match.TeamB = edit_awayteam.text.toString()
        match.Location = edit_location.text.toString()
        match.Group = edit_group.text.toString()
        match.Time = edit_time.text.toString()
    }

    fun set_match_settings()
    {
        edit_hometeam.setText(match.TeamA)
        edit_awayteam.setText(match.TeamB)
        edit_location.setText(match.Location)
        edit_group.setText(match.Group)
        edit_time.setText(match.Time)
    }

    fun get_match(): Match {
        return match
    }
}