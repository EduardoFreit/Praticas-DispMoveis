package ifpe.pdm.praticas

import android.net.Uri
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class ForecastTaskCoroutines(private val listener: MainActivity) {
    private val LOG_TAG = ForecastTask::class.java.simpleName
    private val APPID = ""

    suspend fun doInBackground(vararg params: String): List<String>? {
        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        val locationString = params[0]
        var forecastJson: String? = null
        var forecast: List<String>? = null
        try {
            val builder = Uri.Builder()
                .scheme("https")
                .authority("api.openweathermap.org")
                .appendPath("data/2.5/forecast/daily")
                .appendQueryParameter("q", locationString)
                .appendQueryParameter("mode", "json")
                .appendQueryParameter("units", "metric")
                .appendQueryParameter("cnt", "7")
                .appendQueryParameter("APPID", APPID)
            val url = URL(builder.build().toString())
            urlConnection = url.openConnection() as HttpURLConnection
            urlConnection.requestMethod = "GET"
            urlConnection.connect()
            val inputStream: InputStream = urlConnection.inputStream
            val buffer = StringBuffer()
            reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?
            while (reader.readLine().also { line = it } != null) {
                buffer.append(line).append("\n")
            }
            if (buffer.length == 0) {
                forecastJson = null
            } else {
                forecastJson = buffer.toString()
            }
            forecast = ForecastParser.getDataFromJson(forecastJson, 7)
        } catch (e: IOException) {
            Log.e(LOG_TAG, "Error ", e)
        } catch (e: JSONException) {
            Log.e(LOG_TAG, "JSON Error ", e)
        } finally {
            urlConnection?.disconnect()
            reader?.close()
        }
        return forecast
    }

    suspend fun execute(vararg params: String) {
        val resultStrs = withContext(Dispatchers.IO) {
            doInBackground(*params)
        }
        resultStrs?.let {
            for (s in it) {
                Log.v(LOG_TAG, "Forecast entry: $s")
            }
            listener.showForecast(it)
        }
    }
}