package de.malte.badsheet.classes

import de.malte.badsheet.R
import de.malte.badsheet.utility.ContextUtil

/** Class to hold data from a Match **/
class Match
{
    var teamA: Team = Team("")
    var teamB: Team = Team("")
    var location: String = ""
    var group: String = ""
    var time: String = ""
    var scoreboard: Scoreboard = Scoreboard()
    var winner: String = ""

    /** Calculate all points, sets, games and the Match-winner **/
    fun calcMatch()
    {
        scoreboard.resetValues()
        var list: MutableList<Int>
        // Go over all 8 Games
        for (i in 0..47 step 6)
        {
            list = scoreboard.score.subList(i, i+6)
            addGame(scoreboard.calcGame(list))
        }
        calcMatchWinner()
    }

    /** Calculate winner of a match **/
    private fun calcMatchWinner()
    {
        winner = if (teamA.games.sum() > teamB.games.sum()) {
            teamA.Name
        } else if (teamA.games.sum() < teamB.games.sum()) {
            teamB.Name
        } else {
            ContextUtil.context?.getString(R.string.draw) ?: "Draw"
        }
    }

    /** Update after one Game **/
    private fun addGame(game: Game)
    {
        teamA.sets.add(game.setsA)
        teamB.sets.add(game.setsB)
        teamA.points.add(game.pointsA)
        teamB.points.add(game.pointsB)

        if(game.winner == true)
        {
            teamA.games.add(1)
            teamB.games.add(0)
        }
        else if (game.winner == false)
        {
            teamA.games.add(0)
            teamB.games.add(1)
        }
        else
        {
            teamA.games.add(0)
            teamB.games.add(0)
        }
    }

    fun getTeam(teamName: String): Team
    {
        if (teamA.Name == teamName)
        {
            return teamA
        }
        else if (teamB.Name == teamName)
        {
            return teamB
        }
        return Team("")
    }

    fun setTeam(teamName: String, team: Team)
    {
        if (teamA.Name == teamName)
        {
            teamA = team
        }
        else if (teamB.Name == teamName)
        {
            teamB = team
        }
    }
}