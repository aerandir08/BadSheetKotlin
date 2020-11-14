package de.malte.badsheet.classes

/** Class to hold data from one Team **/
class Team(var Name: String)
{
    var Player = arrayOfNulls<String>(12)

    fun get_pretty_strings(): Array<String?>
    {
        val names = arrayOfNulls<String>(8)
        names[0] = "${Player[0]}\n${Player[1]}"
        names[1] = "${Player[2]}\n${Player[3]}"
        names[2] = "${Player[4]}\n${Player[5]}"
        names[3] = "${Player[6]}"
        names[4] = "${Player[7]}"
        names[5] = "${Player[8]}"
        names[6] = "${Player[9]}"
        names[7] = "${Player[10]}\n${Player[11]}"

        return names
    }
}