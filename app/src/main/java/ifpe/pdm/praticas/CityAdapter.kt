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
        var holder: ViewHolder?

        if (listItem == null) {
            val inflater: LayoutInflater = LayoutInflater.from(context)
            listItem = inflater.inflate(R.layout.city_listitem, parent, false)

            holder = ViewHolder()
            holder.cityName = listItem!!.findViewById((R.id.city_name))
            holder.cityInfo = listItem!!.findViewById((R.id.city_info))

            listItem.tag = holder
        } else {
            holder = view?.tag as ViewHolder
        }

        holder.cityName.text = cities[position].name
        holder.cityInfo.text = cities[position].info

        return listItem
    }
}
