package de.malte.badsheet

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

/** Utility class **/
class Util {
    /** Save given object to SharedPreferences
     * @param act Activity
     * @param obj Object to save
     * @param key Name for the Data
     */
    fun SavePreferences(act: Activity, obj: Any, key: String)
    {
        var sharedPreferences: SharedPreferences = act.getSharedPreferences(act.getString(R.string.sharedPreferences), Context.MODE_PRIVATE)
        var editor: SharedPreferences.Editor = sharedPreferences.edit()
        var gson: Gson = Gson()
        var json_match: String = gson.toJson(obj)
        editor.putString(key, json_match)
        editor.commit()
    }

    /** Load object from SharedPreferences
     * @param act Activity
     * @param key Name of the Data that should be loaded
     * @return Json-String with loaded data
     */
    fun LoadPreferences(act: Activity, key: String): String {
        var sharedpreferences: SharedPreferences = act.getSharedPreferences(act.getString(R.string.sharedPreferences), Context.MODE_PRIVATE)
        var gson: Gson = Gson()
        var json_match = sharedpreferences.getString(key, "")
        if (json_match != null)
        {
            return json_match
        }
        return ""
    }

    /** Load Match from SharedPreferences
     * @param act Activity
     * @return Loaded Match
     */
    fun GetMatch(act: Activity): Match
    {
        var json_match = LoadPreferences(act, "MATCH")
        var gson: Gson = Gson()
        var match = gson.fromJson(json_match, Match::class.java)
        if (match != null)
        {
            return match
        }
        return Match()
    }
}