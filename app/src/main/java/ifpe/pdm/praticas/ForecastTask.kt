package ifpe.pdm.praticas

import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import org.json.JSONException
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


class ForecastTask(private val listener: MainActivity) : AsyncTask<String, Void, List<String>>() {
    private val LOG_TAG = ForecastTask::class.java.simpleName
    private var forecast: List<String>? = null
    private val APPID = ""

    override fun doInBackground(vararg params: String): List<String>? {
        var urlConnection: HttpURLConnection? = null
        var reader: BufferedReader? = null
        val locationString = params[0]
        var forecastJson: String? = null
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
            forecastJson = if (buffer.isEmpty()) {
                null
            } else {
                buffer.toString()
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

    override fun onPostExecute(resultStrs: List<String>) {
        for (s in resultStrs) {
            Log.v(LOG_TAG, "Forecast entry: $s")
        }
        listener.showForecast(resultStrs)
    }
}