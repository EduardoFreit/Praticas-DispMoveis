package ifpe.pdm.praticas

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    companion object {
        val CITIES : Array<String> = arrayOf("Recife", "João Pessoa", "Natal",
            "Fortaleza", "Rio de Janeiro", "São Paulo", "Salvador", "Vitória",
            "Florianópolis", "Porto Alegre", "São Luiz","Teresina",
            "Belém", "Manaus")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView : ListView = findViewById(R.id.list_view)
        listView.adapter = ArrayAdapter(
            this,
            R.layout.city_listitem,
            R.id.city_name,
            CITIES
        )

        listView.onItemClickListener =
            OnItemClickListener { parent: AdapterView<*>, view: View?, position: Int, id: Long ->
                Toast.makeText(
                    parent.context,
                    "Cidade selecionada: " + CITIES[position],
                    Toast.LENGTH_SHORT
                ).show()
            }

    }
}