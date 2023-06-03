package ifpe.pdm.praticas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherForecastAPI {

    companion object {
        const val APPID = "SUA CHAVE AQUI"
    }

    @GET("forecast/daily?APPID=$APPID&mode=json&units=metric&cnt=7")
    fun getForecast(@Query("q") city: String): Call<WeatherForecast>
}