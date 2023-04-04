package ifpe.pdm.praticas

import City
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class CityAdapter(context: Context, resource: Int, private val cities: Array<City>) : ArrayAdapter<City>(context, resource, cities) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        var listItem: View? = view

        if (listItem == null) {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            listItem = inflater.inflate(R.layout.city_listitem, parent, false)
        }

        val cityName: TextView = listItem!!.findViewById(R.id.city_name)
        val cityInfo: TextView = listItem!!.findViewById(R.id.city_info)
        cityName.text = cities[position].name
        cityInfo.text = cities[position].info
        return listItem
    }
}
