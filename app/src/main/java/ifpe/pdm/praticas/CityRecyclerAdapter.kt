package ifpe.pdm.praticas

import City
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject


class CityRecyclerAdapter(private val cities: Array<City>, private val queue: RequestQueue) : RecyclerView.Adapter<CityHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_listitem, parent, false)
        return CityHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        if (cities[position].weather != null) {
            holder.cityInfo.setText(cities[position].weather);
        } else {
            val city = cities[position]
            val weather = holder.cityInfo
            weather.text = "Loading weather..."
            loadInBackground(weather, city)
        }
        holder.bindCity(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    private fun loadInBackground(weatherView: TextView, city: City) {
        val builder = Uri.Builder()
        builder.scheme("https")
            .authority("api.openweathermap.org")
            .appendPath("data/2.5/weather")
            .appendQueryParameter("q", city.name)
            .appendQueryParameter("mode", "json")
            .appendQueryParameter("units", "metric")
            .appendQueryParameter("APPID", "4d70cb4fabb17db423f1cd0e03eebd70")

        val request = JsonObjectRequest(Request.Method.GET, builder.build().toString(), null,
            { response ->
                val weatherStr = getWeather(response)
                if (weatherStr != null) {
                    city.weather = weatherStr
                    weatherView.text = weatherStr
                } else {
                    weatherView.text = "Erro!"
                }
            },
            { error -> weatherView.text = "Erro!" })

        queue.add(request)
    }

    private fun getWeather(forecastJson: JSONObject): String? {
        val OWM_WEATHER = "weather"
        val OWM_TEMPERATURE = "temp"
        val OWM_MAIN = "main"
        val OWM_DESCRIPTION = "description"
        try {
            val weatherArray = forecastJson.getJSONArray(OWM_WEATHER)
            val weatherObject = weatherArray.getJSONObject(0)
            val mainObject = forecastJson.getJSONObject(OWM_MAIN)
            val description = weatherObject.getString(OWM_DESCRIPTION)
            val temp = mainObject.getDouble(OWM_TEMPERATURE)
            return "$description - $temp"
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
    }


}