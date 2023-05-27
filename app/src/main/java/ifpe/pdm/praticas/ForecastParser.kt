package ifpe.pdm.praticas

import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.GregorianCalendar


object ForecastParser {
    private fun getReadableDateString(time: Long): String {
        val shortenedDateFormat = SimpleDateFormat("EEE MMM dd")
        return shortenedDateFormat.format(time)
    }

    private fun formatHighLows(high: Double, low: Double): String {
        val roundedHigh = Math.round(high)
        val roundedLow = Math.round(low)
        return "$roundedLow/$roundedHigh"
    }

    @Throws(JSONException::class)
    fun getDataFromJson(forecastJsonStr: String?, numDays: Int): List<String> {
        val OWM_LIST = "list"
        val OWM_WEATHER = "weather"
        val OWM_TEMPERATURE = "temp"
        val OWM_MAX = "max"
        val OWM_MIN = "min"
        val OWM_DESCRIPTION = "main"
        val forecastJson = JSONObject(forecastJsonStr)
        val weatherArray = forecastJson.getJSONArray(OWM_LIST)
        val cal = GregorianCalendar()
        val resultStrs: MutableList<String> = ArrayList()
        for (i in 0 until weatherArray.length()) {
            // Get the JSON object representing the day
            val dayForecast = weatherArray.getJSONObject(i)
            val weatherObject = dayForecast.getJSONArray(OWM_WEATHER).getJSONObject(0)
            val description = weatherObject.getString(OWM_DESCRIPTION)
            val temperatureObject = dayForecast.getJSONObject(OWM_TEMPERATURE)
            val high = temperatureObject.getDouble(OWM_MAX)
            val low = temperatureObject.getDouble(OWM_MIN)
            val highAndLow = formatHighLows(high, low)
            val date = getReadableDateString(cal.getTimeInMillis())
            resultStrs.add("$date - $description - $highAndLow")
            cal.add(GregorianCalendar.DATE, 1)
        }
        return resultStrs
    }
}
