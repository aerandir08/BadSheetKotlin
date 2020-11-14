package de.malte.badsheet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_scoreboard.*

class ScoreboardActivity : AppCompatActivity()
{
    private var match = Match()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoreboard)
        title = getString(R.string.scoreboard)

        match = Util().GetMatch(this)
        set_scores()
        set_names()
    }

    /** Save Team to sharedPreferences **/
    override fun onPause()
    {
        super.onPause()
        get_scores()
        Util().SaveSharedPreference(this, match, "MATCH")
    }

    /** Get Digits from Edittext
     * @param textfield Textfield to get Data from
     * @return Array of both Points
     */
    fun get_digits(textfield: EditText): ArrayList<Int>
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
    fun get_scores()
    {
        val layout: TableLayout = layout_tablelayout
        var idx = 0
        for (row in layout.children)
        {
            if (row is TableRow)
            {
                for (edit in row.children)
                {
                    if (edit is EditText)
                    {
                        val points = get_digits(edit)
                        match.Scoreboard.Score[idx] = points[0]
                        match.Scoreboard.Score[idx + 1] = points[1]
                        idx += 2
                    }
                }
            }
        }
    }

    /** Set Scores to View **/
    fun set_scores()
    {
        val layout: TableLayout = layout_tablelayout
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
                        text = "${match.Scoreboard.Score[idx]}-${match.Scoreboard.Score[idx+1]}"
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
    fun set_names()
    {
        val layout: TableLayout = layout_tablelayout

        // Build List with alternating values from both teams
        val namesA = match.TeamA.get_pretty_strings()
        val namesB = match.TeamB.get_pretty_strings()
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
                            field.setText(names[idx])
                            idx += 1
                        }
                    }
                }
            }
        }
    }
}