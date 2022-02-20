package de.malte.badsheet.classes

import io.swagger.client.apis.DefaultApi
import io.swagger.client.models.Player
import io.swagger.client.models.Team


class apiClient()
{
    private val client = DefaultApi()
    fun get_teamnames(): Array<Team>
    {
        val teams = client.teamsGet()
        return teams
    }

    fun update_teamnames()
    {
        client.teamsPost()
    }

    fun get_players(teamname: String): Array<Player>
    {
        val players = client.playersTeamnameGet(teamname)
        return players
    }
}