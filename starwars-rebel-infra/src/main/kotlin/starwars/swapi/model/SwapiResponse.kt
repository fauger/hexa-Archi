package starwars.controller.swapi.model

sealed class SwapiResponse{

    class Success(val next : String?,val results : List<SwapiStarship>) : SwapiResponse()
    class Error(val error : String) : SwapiResponse()
}

data class SwapiStarship(val name : String, val passengers : String?)
