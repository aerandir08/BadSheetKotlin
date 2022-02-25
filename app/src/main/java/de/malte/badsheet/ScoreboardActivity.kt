package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import de.malte.badsheet.classes.Match
import de.malte.badsheet.databinding.ActivityScoreboardBinding
import de.malte.badsheet.utility.SharedPref

class ScoreboardActivity : AppCompatActivity()
{
    private lateinit var binding: ActivityScoreboardBinding
    private var match = Match()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreboardBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        title = getString(R.string.scoreboard)

        match = SharedPref().GetMatch(this)
        setScores()
        setNames()
    }

    /** Save Team to sharedPreferences **/
    override fun onPause()
    {
        super.onPause()
        getScores()
        match.calcMatch()
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }

    /** Get Digits from Edittext
     * @param textfield Textfield to get Data from
     * @return Array of both Points
     */
    private fun getDigits(textfield: EditText): ArrayList<Int>
    {
        val value = textfield.text.toString()
        val parts = value.split("-")
        if (parts.size != 2)
        {
            return ArrayList<Int>(listOf(0, 0))
        }
        val points = ArrayList<Int>()
        parts.forEach{points.add(it.toInt())}

        return points
    }

    /** Get Scores from View **/
    private fun getScores()
    {
        val layout: TableLayout = binding.layoutTablelayout
        var idx = 0
        for (row in layout.children)
        {
            if (row is TableRow)
            {
                for (edit in row.children)
                {
                    if (edit is EditText)
                    {
                        val points = getDigits(edit)
                        match.scoreboard.score[idx] = points[0]
                        match.scoreboard.score[idx + 1] = points[1]
                        idx += 2
                    }
                }
            }
        }
    }

    /** Set Scores to View **/
    private fun setScores()
    {
        val layout: TableLayout = binding.layoutTablelayout
        var idx = 0
        var text: String
        for (row in layout.children)
        {
            if (row is TableRow)
            {
                for (edit in row.children)
                {
                    if (edit is EditText)
                    {
                        text = "${match.scoreboard.score[idx]}-${match.scoreboard.score[idx+1]}"
                        if (text == "0-0")
                        {
                            edit.setText("")
                        }
                        else
                        {
                            edit.setText(text)
                        }
                        idx += 2
                    }
                }
            }
        }
    }

    /** Set Names **/
    private fun setNames()
    {
        val layout: TableLayout = binding.layoutTablelayout

        // Build List with alternating values from both teams
        val namesA = match.teamA.getPrettyStrings()
        val namesB = match.teamB.getPrettyStrings()
        val names = namesA.zip(namesB).flatMap { listOf(it.first, it.second) } + namesA.drop(namesB.size)

        var idx = 0
        for (row in layout.children)
        {
            if (row is TableRow)
            {
                for (field in row.children)
                {
                    if (field is TextView)
                    {
                        if (field.text == "")
                        {
                            field.text = names[idx]
                            idx += 1
                        }
                    }
                }
            }
        }
    }
}