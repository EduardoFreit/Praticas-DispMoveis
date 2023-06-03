package ifpe.pdm.praticas

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherForecastAPI {

    companion object {
        const val APPID = "a11ac945f2360e8cf7d496e7cb53dc00"
    }

    @GET("forecast/daily?APPID=$APPID&mode=json&units=metric&cnt=7")
    fun getForecast(@Query("q") city: String): Call<WeatherForecast>
}