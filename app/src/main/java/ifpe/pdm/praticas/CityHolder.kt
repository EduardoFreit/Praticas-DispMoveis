package ifpe.pdm.praticas

import City
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val cityName: TextView
    private val cityInfo: TextView
    private var city: City? = null

    init {
        cityName = itemView.findViewById<TextView>(R.id.city_name)
        cityInfo = itemView.findViewById<TextView>(R.id.city_info)
        itemView.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        Toast.makeText(
            v.context, "Cidade selecionada: " +
                    city!!.name, Toast.LENGTH_SHORT
        ).show()
    }

    fun bindCity(city: City) {
        this.city = city
        cityName.text = city.name
        cityInfo.text = city.info
    }
}
