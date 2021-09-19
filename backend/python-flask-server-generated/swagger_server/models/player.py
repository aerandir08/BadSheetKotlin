# coding: utf-8

from __future__ import absolute_import
from datetime import date, datetime  # noqa: F401

from typing import List, Dict  # noqa: F401

from swagger_server.models.base_model_ import Model
from swagger_server import util


class Player(Model):
    """NOTE: This class is auto generated by the swagger code generator program.

    Do not edit the class manually.
    """
    def __init__(self, name: str=None, sex: str=None):  # noqa: E501
        """Player - a model defined in Swagger

        :param name: The name of this Player.  # noqa: E501
        :type name: str
        :param sex: The sex of this Player.  # noqa: E501
        :type sex: str
        """
        self.swagger_types = {
            'name': str,
            'sex': str
        }

        self.attribute_map = {
            'name': 'name',
            'sex': 'sex'
        }
        self._name = name
        self._sex = sex

    @classmethod
    def from_dict(cls, dikt) -> 'Player':
        """Returns the dict as a model

        :param dikt: A dict.
        :type: dict
        :return: The Player of this Player.  # noqa: E501
        :rtype: Player
        """
        return util.deserialize_model(dikt, cls)

    @property
    def name(self) -> str:
        """Gets the name of this Player.


        :return: The name of this Player.
        :rtype: str
        """
        return self._name

    @name.setter
    def name(self, name: str):
        """Sets the name of this Player.


        :param name: The name of this Player.
        :type name: str
        """
        if name is None:
            raise ValueError("Invalid value for `name`, must not be `None`")  # noqa: E501

        self._name = name

    @property
    def sex(self) -> str:
        """Gets the sex of this Player.


        :return: The sex of this Player.
        :rtype: str
        """
        return self._sex

    @sex.setter
    def sex(self, sex: str):
        """Sets the sex of this Player.


        :param sex: The sex of this Player.
        :type sex: str
        """
        allowed_values = ["male", "female"]  # noqa: E501
        if sex not in allowed_values:
            raise ValueError(
                "Invalid value for `sex` ({0}), must be one of {1}"
                .format(sex, allowed_values)
            )

        self._sex = sex
