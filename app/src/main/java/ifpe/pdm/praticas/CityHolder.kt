package ifpe.pdm.praticas

import City
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.toolbox.NetworkImageView


class CityHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    private val cityName: TextView
    val cityInfo: TextView
    val imageView: NetworkImageView
    private var city: City? = null

    init {
        cityName = itemView.findViewById<TextView>(R.id.city_name)
        cityInfo = itemView.findViewById<TextView>(R.id.city_info)
        imageView = itemView.findViewById<NetworkImageView>(R.id.city_image_weather)
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
        cityInfo.text = city.weather
    }
}
