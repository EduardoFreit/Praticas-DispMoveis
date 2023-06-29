package ifpe.pdm.praticas

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import ifpe.pdm.praticas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonSave.setOnClickListener { this.buttonInsertClick() }
        viewBinding.buttonQuery.setOnClickListener { this.buttonQueryClick() }
        viewBinding.buttonUpdate.setOnClickListener { this.buttonUpdateClick() }
        viewBinding.buttonDelete.setOnClickListener { this.buttonDeleteClick() }
    }

    private fun buttonInsertClick() {
        val name = viewBinding.editName.text.toString()
        val grade = viewBinding.editGrade.text.toString()
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(name, grade)
        editor.apply()
        Toast.makeText(this, "Salvo: $name", Toast.LENGTH_SHORT).show()
    }

    private fun buttonQueryClick() {
        val name = viewBinding.editName.text.toString()
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val grade = prefs.getString(name, "[Não encontrado]")
        viewBinding.editGrade.setText(grade)
    }

    private fun buttonUpdateClick() {
        val name = viewBinding.editName.text.toString()
        val grade = viewBinding.editGrade.text.toString()
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val gradeValue = prefs.getString(name, "[Não encontrado]")
        if(gradeValue.toString() != "[Não encontrado]") {
            val editor = prefs.edit()
            editor.putString(name, grade)
            editor.apply()
            Toast.makeText(this, "Atualizada chave: $name", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Não encontrada a chave: $name", Toast.LENGTH_SHORT).show()
        }
    }

    private fun buttonDeleteClick() {
        val name = viewBinding.editName.text.toString()
        val prefs = getPreferences(Context.MODE_PRIVATE)
        val grade = prefs.getString(name, "[Não encontrado]")
        if(grade.toString() != "[Não encontrado]") {
            val editor = prefs.edit()
            editor.remove(name)
            editor.apply()
            Toast.makeText(this, "Removida chave: $name", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Não encontrada a chave: $name", Toast.LENGTH_SHORT).show()
        }
    }

}