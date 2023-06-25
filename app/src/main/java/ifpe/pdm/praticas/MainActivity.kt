package ifpe.pdm.praticas

import City
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley


class MainActivity : AppCompatActivity() {

    private lateinit var queue: RequestQueue

    companion object {
        val CITIES : Array<City> = arrayOf(
            City("Recife"),
            City("João Pessoa"),
            City("Natal"),
            City("Fortaleza"),
            City("Salvador"),
            City("São Luiz"),
            City("Teresina"),
            City("Rio de Janeiro"),
            City("São Paulo"),
            City("Vitória"),
            City("Belo Horizonte"),
            City("Florianópolis"),
            City("Curitiba"),
            City("Porto Alegre"),
            City("Macapá"),
            City("Porto Velho"),
            City("Palmas"),
            City("Boa Vista"),
            City("Belém"),
            City("Rio Branco"),
            City("Manaus"),
            City("Goiania"),
            City("Cuiabá"),
            City("Campo Grande")
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        this.queue = Volley.newRequestQueue(this)

        val recyclerView: RecyclerView = findViewById(R.id.list_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CityRecyclerAdapter(CITIES, this.queue)

    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll(this)
    }

}