import requests
import json
import re

from bs4 import BeautifulSoup
from swagger_server.models.player import Player

# PLAYER_URL = 'https://www.turnier.de/sport/teamrankingplayers.aspx?id=915EE244-50FD-429B-B7C8-C02CDB0B3AA1&tid='
TEAM_URL = 'https://www.turnier.de/sport/league/team?id=915EE244-50FD-429B-B7C8-C02CDB0B3AA1&team='

def teams_post():
    """Updates the list of teams in the backend.

    :rtype: None
    """
    teamnames = {}

    for number in range(1, 850):
        team_url = TEAM_URL + str(number)

        # http request
        session = requests.Session()
        session.post('https://www.turnier.de/cookiewall/Save')
        response = session.get(team_url)

        if response.status_code == 200:

            # Parse http response and search for teamname
            html_doc = response.content.decode('utf-8')
            teamname = re.search(r'Mannschaft: (.*?) \(', html_doc)

            if teamname:
                # Save teamname in dict
                print('Found: ' + teamname.group(1))
                teamnames[teamname.group(1)] = str(number)

    # Write teamnames to json
    with open('teamnames.json', 'w', encoding='utf8') as File:
        json.dump(teamnames, File, ensure_ascii=False)

teams_post()