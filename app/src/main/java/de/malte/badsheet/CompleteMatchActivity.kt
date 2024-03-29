package de.malte.badsheet

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import de.malte.badsheet.classes.Match
import de.malte.badsheet.databinding.ActivityCompleteMatchBinding
import de.malte.badsheet.utility.SharedPref

class CompleteMatchActivity : AppCompatActivity()
{

    private lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        val binding: ActivityCompleteMatchBinding = DataBindingUtil.setContentView(this, R.layout.activity_complete_match)
        binding.completeMatch = this

        title = getString(R.string.complete_match)

        match = SharedPref().GetMatch(this)
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }

    /** Called when Button Show Results is clicked. **/
    fun showResults()
    {
        TODO()
    }

    /** Called when Button Sign Home is clicked. **/
    fun signHome()
    {
        val intent = Intent(this, SignatureActivity::class.java).apply {putExtra("TEAM", getString(R.string.hometeam))}
        startActivity(intent)
    }

    /** Called when Button Sign Away is clicked. **/
    fun signAway()
    {
        val intent = Intent(this, SignatureActivity::class.java).apply {putExtra("TEAM", getString(R.string.awayteam))}
        startActivity(intent)
    }

    /** Called when Button Finish Match is clicked. **/
    fun finishMatch()
    {
        TODO()
    }

    /** Called when Button Share Results is clicked. **/
    fun shareResults()
    {
        TODO()
    }
}