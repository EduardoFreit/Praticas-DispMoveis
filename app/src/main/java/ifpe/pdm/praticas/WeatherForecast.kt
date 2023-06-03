package ifpe.pdm.praticas

class WeatherForecast {
    data class City(
        val id: String,
        val name: String,
        val country: String,
        val population: Long,
        val timezone: Long,
        val coord: Coordinates
    ) {
        data class Coordinates(
            val lon: Float,
            val lat: Float
        )
    }

    data class Forecast(
        val dt: Long,
        val sunrise: Long,
        val sunset: Long,
        val pressure: Int,
        val humidity: Int,
        val speed: Float,
        val deg: Int,
        val clouds: Int,
        val pop: Float,
        val rain: Float,
        val temp: Temperature,
        val feels_like: Temperature,
        val weather: List<Weather>
    ) {
        data class Temperature(
            val day: Float,
            val min: Float,
            val max: Float,
            val night: Float,
            val eve: Float,
            val morn: Float
        )

        data class Weather(
            val id: Int,
            val main: String,
            val description: String,
            val icon: String
        )
    }

    val city: City? = null
    val cod: String? = null
    val message: Float? = null
    val cnt: Int = 0
    val list: List<Forecast>? = null
}
