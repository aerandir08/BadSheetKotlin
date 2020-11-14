package de.malte.badsheet

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

/** Utility class **/
class Util {
    /** Save given object to SharedPreferences
     * @param act Activity
     * @param obj Object to save
     * @param key Name for the Data
     */
    fun SaveSharedPreference(act: Activity, obj: Any, key: String)
    {
        val sharedPreferences: SharedPreferences = act.getSharedPreferences(act.getString(R.string.sharedPreferences), Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        val gson = Gson()
        val json_match: String = gson.toJson(obj)
        editor.putString(key, json_match)
        editor.apply()
    }

    /** Load object from SharedPreferences
     * @param act Activity
     * @param key Name of the Data that should be loaded
     * @return Json-String with loaded data
     */
    fun LoadSharedPreference(act: Activity, key: String): String {
        val sharedpreferences: SharedPreferences = act.getSharedPreferences(act.getString(R.string.sharedPreferences), Context.MODE_PRIVATE)
        val json_match = sharedpreferences.getString(key, "")
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
        val json_match = LoadSharedPreference(act, "MATCH")
        val gson = Gson()
        val match = gson.fromJson(json_match, Match::class.java)
        if (match != null)
        {
            return match
        }
        return Match()
    }
}