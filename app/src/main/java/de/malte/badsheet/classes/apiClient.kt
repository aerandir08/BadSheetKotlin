package de.malte.badsheet.classes

import io.swagger.client.apis.DefaultApi

class apiClient()
{
    private val client = DefaultApi()
    fun get_teamnames(): MutableList<String>
    {
        val teams = client.teamsGet()
        val returnList : MutableList<String> = arrayListOf()
        for(team in teams)
        {
            returnList.add(team.name)
        }
        return returnList
    }

    fun update_teamnames()
    {
        client.teamsPost()
    }

    fun get_players(teamname: String): MutableList<String>
    {
        val players = client.playersTeamnameGet(teamname)
        val returnList : MutableList<String> = arrayListOf()
        for(player in players)
        {
            returnList.add(player.name)
        }
        return returnList
    }
}