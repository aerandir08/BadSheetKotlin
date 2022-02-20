/**
 * BadSheet API
 * Backend for the app BadSheet to get team and player information from turnier.de
 *
 * OpenAPI spec version: 0.0.1
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */
package io.swagger.client.models


/**
 * 
 * @param name 
 * @param sex 
 */
data class Player (

    val name: kotlin.String,
    val sex: Player.Sex
) {
    /**
    * 
    * Values: MALE,FEMALE
    */
    enum class Sex(val value: kotlin.String){
        MALE("male"),
        FEMALE("female");
    }
}