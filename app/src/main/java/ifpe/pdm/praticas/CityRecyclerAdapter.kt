package ifpe.pdm.praticas

import City
import android.graphics.Bitmap
import android.net.Uri
import android.util.LruCache
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.ImageLoader
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONException
import org.json.JSONObject


class CityRecyclerAdapter(private val cities: Array<City>, private val queue: RequestQueue) : RecyclerView.Adapter<CityHolder>() {

    private var loader: ImageLoader = ImageLoader(queue, object : ImageLoader.ImageCache {
        private val mCache = LruCache<String, Bitmap>(10)
        override fun putBitmap(url: String, bitmap: Bitmap) {
            mCache.put(url, bitmap)
        }
        override fun getBitmap(url: String): Bitmap? {
            return mCache.get(url)
        }
    })


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_listitem, parent, false)
        return CityHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        if (cities[position].weather != null && cities[position].urlImagem != null ) {
            holder.cityInfo.text = cities[position].weather
            holder.imageView.setImageUrl(cities[position].urlImagem, this.loader)
        } else {
            val city = cities[position]
            val weather = holder.cityInfo
            weather.text = "Loading weather..."
            loadInBackground(holder, city)
        }
        holder.bindCity(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    private fun loadInBackground(holder: CityHolder, city: City) {
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
                val weatherIcon = getImageURL(response)
                if (weatherStr != null && weatherIcon != null) {
                    city.weather = weatherStr
                    city.urlImagem = weatherIcon
                    holder.cityInfo.text = weatherStr
                    holder.imageView.setImageUrl(weatherIcon, this.loader)
                } else {
                    holder.cityInfo.text = "Erro!"
                }
            },
            { error -> holder.cityInfo.text = "Erro!" })

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

    private fun getImageURL(forecastJson: JSONObject): String? {
        val OWM_WEATHER = "weather"
        val OWM_ICON = "icon"
        val OWM_MAIN = "main"
        try {
            val weatherArray = forecastJson.getJSONArray(OWM_WEATHER)
            val weatherObject = weatherArray.getJSONObject(0)
            val mainObject = forecastJson.getJSONObject(OWM_MAIN)
            val icon = weatherObject.getString(OWM_ICON)
            return "https://openweathermap.org/img/wn/${icon}@2x.png"
        } catch (e: JSONException) {
            e.printStackTrace()
            return null
        }
    }


}