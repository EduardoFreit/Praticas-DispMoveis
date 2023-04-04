package ifpe.pdm.praticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

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
        listView.adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, CITIES)
    }
}