package de.malte.badsheet.classes

import io.swagger.client.apis.DefaultApi
import java.net.InetAddress

class ApiClient()
{
    private val client = DefaultApi()

    /**
     * Returns all available teamnames from server.
     */
    fun getTeamnames(): MutableList<String>
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

    /**
     * Updates the teamnames json on the server.
     */
    fun updateTeamnames()
    {
        if (isInternetAvailable())
        {
            client.teamsPost()
        }
    }

    /**
     * Returns all players from one team.
     * @param teamname
     */
    fun getPlayers(teamname: String): MutableList<String>
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

    /**
     * Checks if internet and the server are available.
     */
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