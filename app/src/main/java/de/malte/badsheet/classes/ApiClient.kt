package de.malte.badsheet.classes

import io.swagger.client.apis.DefaultApi
import java.net.InetAddress

class ApiClient()
{
    private val client = DefaultApi()

    /**
     * Returns all available team names from server.
     */
    fun getTeamNames(): MutableList<String>
    {
        return if (isInternetAvailable()) {
            val teams = client.teamsGet()
            val returnList: MutableList<String> = arrayListOf()
            for (team in teams) {
                returnList.add(team.name)
            }
            returnList
        } else {
            arrayListOf()
        }
    }

    /**
     * Updates the teamnames json on the server.
     */
    fun updateTeamNames()
    {
        if (isInternetAvailable())
        {
            client.teamsPost()
        }
    }

    /**
     * Returns all players from one team.
     * @param teamName
     */
    fun getPlayers(teamName: String): MutableList<String>
    {
        return if (isInternetAvailable()) {
            val players = client.playersTeamnameGet(teamName)
            val returnList: MutableList<String> = arrayListOf()
            for (player in players) {
                returnList.add(player.name)
            }
            returnList
        } else {
            arrayListOf()
        }
    }

    /**
     * Checks if internet and the server are available.
     */
    private fun isInternetAvailable(): Boolean
    {
        return try {
            val inetAddress: InetAddress = InetAddress.getByName("45.94.211.170")
            !inetAddress.equals("")
        } catch (e: Exception) {
            false
        }
    }
}