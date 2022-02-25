package de.malte.badsheet.classes

import java.util.*
import kotlin.collections.ArrayList

enum class HOME_AWAY
{
    HOME, AWAY
}
/** Class to hold data from one Team **/
class Team(var Name: String)
{
    var player = ArrayList<String>(Collections.nCopies(12, ""))
    var points: ArrayList<Int> = arrayListOf()
    var sets: ArrayList<Int> = arrayListOf()
    var games: ArrayList<Int> = arrayListOf()
    var homeAway: HOME_AWAY = HOME_AWAY.HOME

    fun getPrettyStrings(): Array<String?>
    {
        val names = arrayOfNulls<String>(8)
        names[0] = "${player[0]}\n${player[1]}"
        names[1] = "${player[2]}\n${player[3]}"
        names[2] = "${player[4]}\n${player[5]}"
        names[3] = player[6]
        names[4] = player[7]
        names[5] = player[8]
        names[6] = player[9]
        names[7] = "${player[10]}\n${player[11]}"

        return names
    }
}