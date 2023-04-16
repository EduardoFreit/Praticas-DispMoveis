package ifpe.pdm.praticas

import City
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class CityRecyclerAdapter(private val cities: Array<City>) : RecyclerView.Adapter<CityHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityHolder {
        val inflatedView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.city_listitem, parent, false)
        return CityHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: CityHolder, position: Int) {
        holder.bindCity(cities[position])
    }

    override fun getItemCount(): Int {
        return cities.size
    }
}