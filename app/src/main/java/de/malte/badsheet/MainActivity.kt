package de.malte.badsheet

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import de.malte.badsheet.classes.Match
import de.malte.badsheet.utility.SharedPref
import java.io.*

class MainActivity : AppCompatActivity()
{
    private var match = Match()

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        match = SharedPref().GetMatch(this)
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }

    /** #############################################################
     *
     *  Button Functions
     *
     * ##############################################################
     */

    /** Called when Button Match Settings is clicked. **/
    fun openMatchSettings(view: View)
    {
        val intent = Intent(this, MatchActivity::class.java).apply {}
        startActivity(intent)
    }

    /** Called when a Team is clicked. **/
    fun openTeam(team: String)
    {
        val intent: Intent
        if(team == "home")
        {
            intent = Intent(this, TeamActivity::class.java).apply {putExtra("TEAM", match.TeamA.Name)}
        }
        else
        {
            intent = Intent(this, TeamActivity::class.java).apply {putExtra("TEAM", match.TeamB.Name)}
        }
        startActivity(intent)
    }

    /** Called when TeamA is clicked **/
    fun openTeamA(view: View)
    {
        openTeam("home")
    }

    /** Called when TeamB is clicked **/
    fun openTeamB(view: View)
    {
        openTeam("away")
    }

    /** Called when Button Scoreboard is clicked. **/
    fun openScoreboard(view: View)
    {
        val intent = Intent(this, ScoreboardActivity::class.java).apply {}
        startActivity(intent)
    }

    /** #############################################################
     *
     *  Menu Functions
     *
     * ##############################################################
     */

    /** Create menu **/
    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    /** Set items in menu **/
    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when (item.itemId)
        {
            R.id.save -> {
                saveMatch()
                true
            }
            R.id.load -> {
                loadChooser()
                true
            }
            R.id.reset -> {
                resetMatch()
                true
            }R.id.remove -> {
                removeChooser()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    /** Open Load Chooser Dialog **/
    fun loadChooser()
    {
        val files: Array<String> = fileList()
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose Match")
        builder.setItems(files) { _, which -> loadMatch(files[which])}
        builder.show()
    }

    /** Open Remove Chooser Dialog **/
    fun removeChooser()
    {
        val files: Array<String> = fileList()
        val builder = AlertDialog.Builder(this)
        val selected = ArrayList<Int>()

        builder.setTitle("Remove Matches")
        builder.setMultiChoiceItems(files, null) { _, which, isChecked ->
            if (isChecked)
            {
                selected.add(which)
            }
            else if (selected.contains(which))
            {
                selected.remove(Integer.valueOf(which))
            }
        }
        builder.setPositiveButton("Remove") { _, i ->
            for (j in selected.indices)
            {
                removeFile(files[selected[j]])
            }
        }
        builder.show()
    }

    /** Save Match to harddrive **/
    fun saveMatch()
    {
        val filename: String = match.TeamA.Name + "_" + match.TeamB.Name + "_" + match.Time + ".json"
        val gson = Gson()
        val jsonData: String = gson.toJson(match)
        val fileOutput = openFileOutput(filename, Context.MODE_PRIVATE)
        val oos = OutputStreamWriter(fileOutput)
        oos.write(jsonData)
        oos.close()
    }

    /** Load Match from harddrive
     * @param filename File which should be loaded
     */
    fun loadMatch(filename: String)
    {
        val path = "$filesDir/$filename"
        if (File(path).exists())
        {
            val fis = FileInputStream(path)
            val ois = InputStreamReader(fis)
            val jsonData: String = ois.readText()
            val gson = Gson()
            match = gson.fromJson(jsonData, Match::class.java)
            SharedPref().SaveSharedPreference(this, match, "MATCH")
        }
        else
        {
            Toast.makeText(this, R.string.errorfile, Toast.LENGTH_LONG).show()
        }
    }

    /** Remove File
     * @param filename File which should be deleted
     */
    fun removeFile(filename: String)
    {
        val path = "$filesDir/$filename"
        val file = File(path)
        if (file.exists())
        {
            file.delete()
        }
    }

    /** Reset the current Match **/
    fun resetMatch()
    {
        val match = Match()
        SharedPref().SaveSharedPreference(this, match, "MATCH")
    }


}