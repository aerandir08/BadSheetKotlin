# coding: utf-8

from __future__ import absolute_import

from flask import json
from six import BytesIO

from swagger_server.models.player import Player  # noqa: E501
from swagger_server.models.team import Team  # noqa: E501
from swagger_server.test import BaseTestCase


class TestDefaultController(BaseTestCase):
    """DefaultController integration test stubs"""

    def test_players_teamname_get(self):
        """Test case for players_teamname_get

        Returns all players of a team.
        """
        response = self.client.open(
            '/v1/players/{teamname}'.format(teamname='teamname_example'),
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_teams_get(self):
        """Test case for teams_get

        Returns a list of all teams.
        """
        response = self.client.open(
            '/v1/teams',
            method='GET')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))

    def test_teams_post(self):
        """Test case for teams_post

        Updates the list of teams in the backend.
        """
        response = self.client.open(
            '/v1/teams',
            method='POST')
        self.assert200(response,
                       'Response body is : ' + response.data.decode('utf-8'))


if __name__ == '__main__':
    import unittest
    unittest.main()
