package ifpe.pdm.praticas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ifpe.pdm.praticas.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.GregorianCalendar

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var forecastAPI: WeatherForecastAPI
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonOk.setOnClickListener {
            this.buttonOkClick()
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        forecastAPI = retrofit.create(WeatherForecastAPI::class.java)

    }

    private fun buttonOkClick() {
        val cityName = viewBinding.editCity.text.toString()
        val call = forecastAPI.getForecast(cityName)
        call.enqueue(object : Callback<WeatherForecast> {
            override fun onResponse(call: Call<WeatherForecast>, response: Response<WeatherForecast>) {
                val forecast = response.body()
                val cal = GregorianCalendar()
                val forecastList = ArrayList<String>()
                for (fc in forecast?.list ?: emptyList()) {
                    val tempStr = ForecastParser.formatHighLows(fc.temp.min, fc.temp.max)
                    val dateStr = ForecastParser.getReadableDateString(cal.timeInMillis)
                    val fcStr = "$dateStr - ${fc.weather[0].description} - $tempStr"
                    forecastList.add(fcStr)
                    cal.add(GregorianCalendar.DATE, 1)
                }
                showForecast(forecastList)
            }

            override fun onFailure(call: Call<WeatherForecast>, t: Throwable) {
                val toast = Toast.makeText(
                    this@MainActivity,
                    "Previsão não encontrada para $cityName",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        })
    }

    fun showForecast(forecast: List<String>?) {
        if (forecast == null) {
            val cityName = viewBinding.editCity.text.toString()
            val toast = Toast.makeText(
                this, "Previsão não encontrada para " +
                        cityName, Toast.LENGTH_SHORT
            )
            toast.show()
        } else {
            val data = ArrayList<CharSequence>(forecast)
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putCharSequenceArrayListExtra("data", data)
            startActivity(intent)
        }
    }
}