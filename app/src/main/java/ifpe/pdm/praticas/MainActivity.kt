package ifpe.pdm.praticas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessage(view: View) {
        val intent : Intent = Intent(this, DisplayMessageActivity::class)
        val editText : EditText = findViewById<EditText>(R.id.edit_message)
        val message : String = editText.text.toString();

        intent.putExtra(EXTRA_MSG, message)

        startActivity(intent);
    }
    companion object {
        const val EXTRA_MSG = "Pratica01.MESSAGE"
    }
}