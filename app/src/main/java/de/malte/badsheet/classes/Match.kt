package de.malte.badsheet.classes

import de.malte.badsheet.R
import de.malte.badsheet.utility.ContextUtil

/** Class to hold data from a Match **/
class Match
{
    var TeamA: Team = Team("")
    var TeamB: Team = Team("")
    var Location: String = ""
    var Group: String = ""
    var Time: String = ""
    var Scoreboard: Scoreboard = Scoreboard()
    var Winner: String = ""

    /** Calculate all points, sets, games and the Match-winner **/
    fun calc_match()
    {
        Scoreboard.reset_values()
        var list: MutableList<Int>
        // Go over all 8 Games
        for (i in 0..47 step 6)
        {
            list = Scoreboard.Score.subList(i, i+6)
            add_game(Scoreboard.calc_game(list))
        }
        calc_match_winner()
    }

    /** Calculate winner of a match **/
    private fun calc_match_winner()
    {
        if (TeamA.Games.sum() > TeamB.Games.sum())
        {
            Winner = TeamA.Name
        }
        else if (TeamA.Games.sum() < TeamB.Games.sum())
        {
            Winner = TeamB.Name
        }
        else
        {
            Winner = ContextUtil.context?.getString(R.string.draw) ?: "Draw"
        }
    }

    /** Update after one Game **/
    private fun add_game(game: Game)
    {
        TeamA.Sets.add(game.setsA)
        TeamB.Sets.add(game.setsB)
        TeamA.Points.add(game.pointsA)
        TeamB.Points.add(game.pointsB)

        if(game.winner == true)
        {
            TeamA.Games.add(1)
            TeamB.Games.add(0)
        }
        else if (game.winner == false)
        {
            TeamA.Games.add(0)
            TeamB.Games.add(1)
        }
        else
        {
            TeamA.Games.add(0)
            TeamB.Games.add(0)
        }
    }

    fun get_team(teamname: String): Team
    {
        if (TeamA.Name == teamname)
        {
            return TeamA
        }
        else if (TeamB.Name == teamname)
        {
            return TeamB
        }
        return Team("")
    }

    fun set_team(teamname: String, team: Team)
    {
        if (TeamA.Name == teamname)
        {
            TeamA = team
        }
        else if (TeamB.Name == teamname)
        {
            TeamB = team
        }
    }
}