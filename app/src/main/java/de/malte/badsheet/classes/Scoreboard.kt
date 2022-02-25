package de.malte.badsheet.classes

import java.util.*
import kotlin.collections.ArrayList

class Scoreboard
{
    var score = ArrayList<Int>(Collections.nCopies(48, 0))
    var setsA = 0
    var setsB = 0
    var gamesA = 0
    var gamesB = 0
    var pointsA = 0
    var pointsB = 0
    var winner: Boolean? = null

    /** Reset all values before calculating **/
    fun resetValues()
    {
        setsA = 0
        setsB = 0
        gamesA = 0
        gamesB = 0
        pointsA = 0
        pointsB = 0
        winner = null
    }

    /** Calculate one Game **/
    fun calcGame(p: MutableList<Int>): Game
    {
        val game = Game()
        for (i in 0..4 step 2)
        {
            if (p[i] > p[i+1])
            {
                game.setsA =+ 1
            }
            else if (p[i] < p[i+1])
            {
                game.setsB =+ 1
            }
            game.pointsA += p[i]
            game.pointsB += p[i+1]
        }

        game.whoWins()
        return game
    }
}

class Game
{
    var setsA = 0
    var setsB = 0
    var pointsA = 0
    var pointsB = 0
    var winner: Boolean? = null

    fun whoWins()
    {
        if (setsA > setsB)
        {
            winner = true
        }
        else if (setsA < setsB)
        {
            winner = false
        }
        else
        {
            winner = null
        }
    }
}