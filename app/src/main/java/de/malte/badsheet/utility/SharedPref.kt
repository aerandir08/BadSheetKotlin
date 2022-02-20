package de.malte.badsheet.utility

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import de.malte.badsheet.R
import de.malte.badsheet.classes.Match

/** Class for saving SharedPreferences **/
class SharedPref {
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

    fun saveArray(array: Array<String?>, arrayName: String, mContext: Context): Boolean {
        val prefs = mContext.getSharedPreferences("preferencename", 0)
        val editor = prefs.edit()
        editor.putInt(arrayName + "_size", array.size)
        for (i in array.indices) editor.putString(arrayName + "_" + i, array[i])
        return editor.commit()
    }

    fun loadArray(arrayName: String, mContext: Context): Array<String?> {
        val prefs = mContext.getSharedPreferences("preferencename", 0)
        val size = prefs.getInt(arrayName + "_size", 0)
        val array = arrayOfNulls<String>(size)
        for (i in 0 until size) array[i] = prefs.getString(arrayName + "_" + i, null)
        return array
    }
}