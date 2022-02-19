import json
import requests
import re

from bs4 import BeautifulSoup

from swagger_server.models.player import Player  # noqa: E501
from swagger_server.models.team import Team  # noqa: E501

TEAM_URL = "https://www.turnier.de/sport/league/team?id=915EE244-50FD-429B-B7C8-C02CDB0B3AA1&team="
PLAYER_URL = "https://www.turnier.de/sport/teamrankingplayers.aspx?id=915EE244-50FD-429B-B7C8-C02CDB0B3AA1&tid="


def players_teamname_get(teamname):  # noqa: E501
    """Returns all players of a team.

    :param teamname: Name of the team.
    :type teamname: str

    :rtype: List[Player]
    """
    # Import teamnames
    teamnames_file = "teamnames.json"
    with open(teamnames_file, "r", encoding="utf8") as File:
        teamnames = json.load(File)

    team_url = PLAYER_URL + teamnames[teamname]

    session = requests.Session()
    session.post("https://www.turnier.de/cookiewall/Save")
    response = session.get(team_url)

    players = []
    if response.status_code == 200:
        html_doc = response.content
        soup = BeautifulSoup(html_doc, "html.parser")

        for table in soup.findAll("table", {"class": "ruler"}):
            for child in table.findAll("caption"):
                if "Herren" in child.getText():
                    sex = "male"
                elif "Damen" in child.getText():
                    sex = "female"
                else:
                    pass
                for td in table.findAll("td", {"id": "playercell"}):
                    name_raw = td.getText()
                    name = name_raw.split(", ")
                    name = name[1] + " " + name[0]
                    player = Player(name=name, sex=sex)

                    if player not in players:
                        players.append(player)

    return players


def teams_get():
    """Returns a list of all teams.

    :rtype: List[Team]
    """
    # Reat teamnames from json
    try:
        with open("teamnames.json", "r", encoding="utf8") as File:
            teamnames = json.load(File)

        return [Team(name=name, id=id) for name, id in teamnames.items()]
    except FileNotFoundError:
        return []


def teams_post():
    """Updates the list of teams in the backend.

    :rtype: None
    """
    teamnames = {}

    for number in range(1, 850):
        team_url = TEAM_URL + str(number)

        # http request
        session = requests.Session()
        session.post("https://www.turnier.de/cookiewall/Save")
        response = session.get(team_url)

        if response.status_code == 200:

            # Parse http response and search for teamname
            html_doc = response.content.decode("utf-8")
            teamname = re.search(r"Mannschaft: (.*?) \(", html_doc)

            if teamname:
                # Save teamname in dict
                print("Found: " + teamname.group(1))
                teamnames[teamname.group(1)] = str(number)

    # Write teamnames to json
    with open("teamnames.json", "w", encoding="utf8") as File:
        json.dump(teamnames, File, ensure_ascii=False)
