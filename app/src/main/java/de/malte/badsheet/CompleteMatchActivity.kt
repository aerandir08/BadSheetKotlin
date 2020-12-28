package de.malte.badsheet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import de.malte.badsheet.classes.Match
import de.malte.badsheet.utility.SharedPref

class CompleteMatchActivity : AppCompatActivity() {

    private lateinit var match: Match

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_complete_match)

        match = SharedPref().GetMatch(this)
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }

    /** Called when Button Show Results is clicked. **/
    fun showResults(view: View)
    {
        TODO()
    }

    /** Called when Button Sign Home is clicked. **/
    fun signHome(view: View)
    {
        TODO()
    }

    /** Called when Button Sign Away is clicked. **/
    fun signAway(view: View)
    {
        TODO()
    }

    /** Called when Button Finish Match is clicked. **/
    fun finishMatch(view: View)
    {
        TODO()
    }

    /** Called when Button Share Results is clicked. **/
    fun shareResults(view: View)
    {
        TODO()
    }
}