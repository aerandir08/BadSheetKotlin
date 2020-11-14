package de.malte.badsheet.classes

/** Class to hold data from a Match **/
class Match
{
    var TeamA: Team = Team("")
    var TeamB: Team = Team("")
    var Location: String = ""
    var Group: String = ""
    var Time: String = ""
    var Scoreboard: Scoreboard = Scoreboard()

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