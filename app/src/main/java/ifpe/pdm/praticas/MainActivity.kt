package ifpe.pdm.praticas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ifpe.pdm.praticas.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonOk.setOnClickListener {
            this.buttonOkClick(it)
        }
    }

    private fun buttonOkClick(view: View?) {
        val cityName = (findViewById<View>(viewBinding.editCity.id) as EditText).text.toString()
        ForecastTask(this).execute(cityName)
    }

    fun showForecast(forecast: List<String>?) {
        if (forecast == null) {
            val cityName = (findViewById<View>(viewBinding.editCity.id) as EditText).text.toString()
            val toast = Toast.makeText(
                this, "Previsão não encontrada para " +
                        cityName, Toast.LENGTH_SHORT
            )
            toast.show()
        } else {
            val data = ArrayList<CharSequence>(forecast)
            val intent = Intent(this, ForecastActivity::class.java)
            intent.putCharSequenceArrayListExtra("data", data)
            startActivity(intent)
        }
    }
}