package de.malte.badsheet.classes

import io.swagger.client.apis.DefaultApi
import java.net.InetAddress

class apiClient()
{
    private val client = DefaultApi()
    fun get_teamnames(): MutableList<String>
    {
        if (isInternetAvailable())
        {
            val teams = client.teamsGet()
            val returnList: MutableList<String> = arrayListOf()
            for (team in teams) {
                returnList.add(team.name)
            }
            return returnList
        }
        else
        {
            return arrayListOf()
        }
    }

    fun update_teamnames()
    {
        if (isInternetAvailable())
        {
            client.teamsPost()
        }
    }

    fun get_players(teamname: String): MutableList<String>
    {
        if (isInternetAvailable())
        {
            val players = client.playersTeamnameGet(teamname)
            val returnList: MutableList<String> = arrayListOf()
            for (player in players) {
                returnList.add(player.name)
            }
            return returnList
        }
        else
        {
            return arrayListOf()
        }
    }

    private fun isInternetAvailable(): Boolean
    {
        try {
            val ipAddr: InetAddress = InetAddress.getByName("45.94.211.170")
             return!ipAddr.equals("")
        }
        catch (e: Exception)
        {
            return false
        }
    }
}