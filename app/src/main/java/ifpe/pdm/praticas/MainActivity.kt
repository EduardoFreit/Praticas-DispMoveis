package ifpe.pdm.praticas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessage(view: View) {
        val intent : Intent = Intent(this, DisplayMessageActivity::class.java)
        val editText : EditText = findViewById<EditText>(R.id.edit_message)
        val message : String = editText.text.toString();

        intent.putExtra(EXTRA_MSG, message)

        startActivity(intent);
    }
    companion object {
        const val EXTRA_MSG = "Pratica01.MESSAGE"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater : MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.action_search -> {
                openSearch()
                return true
            }
            R.id.action_settings -> {
                openSettings()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun openSettings() {
        Toast.makeText(this, "Não Implementada.", Toast.LENGTH_LONG).show()
    }

    private fun openSearch() {
        Toast.makeText(this, "Não Implementada.", Toast.LENGTH_LONG).show()
    }
}