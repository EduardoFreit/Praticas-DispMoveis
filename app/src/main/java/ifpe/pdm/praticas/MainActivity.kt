package ifpe.pdm.praticas

import City
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    companion object {
        val CITIES : Array<City> = arrayOf(
            City("Recife", "Capital de Pernambuco"),
            City("João Pessoa", "Capital da Paraíba"),
            City("Natal", "Capital do Rio Grande do Norte"),
            City("Fortaleza", "Capital do Ceará"),
            City("Salvador","Capital da Bahia"),
            City("São Luiz","Capital do Maranhão"),
            City("Teresina","Capital do Piauí"),
            City("Rio de Janeiro", "Capital do Rio de Janeiro"),
            City("São Paulo","Capital de São Paulo"),
            City("Vitória","Capital do Espirito Santo"),
            City("Belo Horizonte","Capital do Minas Gerais"),
            City("Florianópolis","Capital de Santa Catarina"),
            City("Curitiba","Capital do Paraná"),
            City("Porto Alegre","Capital do Rio Grande do Sul"),
            City("Macapá","Capital do Amapá"),
            City("Porto Velho","Capital de Rondônia"),
            City("Palmas","Capital do Tocantins"),
            City("Boa Vista","Capital do Roraima"),
            City("Belém","Capital do Pará"),
            City("Rio Branco","Capital do Acre"),
            City("Manaus","Capital do Amazonas"),
            City("Goiania","Capital do Goias"),
            City("Cuiabá","Capital do Mato Grosso"),
            City("Campo Grande","Capital do Mato Grosso do Sul")
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.list_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CityRecyclerAdapter(CITIES)

    }
}