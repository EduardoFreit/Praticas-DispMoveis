package ifpe.pdm.praticas

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import ifpe.pdm.praticas.databinding.ActivityHomeBinding
import ifpe.pdm.praticas.model.Message


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var fbAuth: FirebaseAuth
    private lateinit var authListener: FirebaseAuthListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.buttonSair.setOnClickListener { this.sair() }

        setContentView(binding.root)

        this.fbAuth = FirebaseAuth.getInstance()
        this.authListener = FirebaseAuthListener(this)

        binding.sendMessage.setOnClickListener {
            val userName = "Test"
            val message: String? = binding.editMessage.text.toString()
            val newMessage = Message(userName, message)
            this.showMessage(newMessage)
        }
    }
    override fun onStart() {
        super.onStart()
        fbAuth.addAuthStateListener(authListener)
    }
    override fun onStop() {
        super.onStop()
        fbAuth.removeAuthStateListener(authListener)
    }

    private fun showMessage(message: Message) {
        val tvMsg = TextView(this)
        tvMsg.text = message.name + ": " + message.text
        binding.chatArea.addView(tvMsg)
    }
    private fun sair() {
        val mAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        if (user != null) {
            mAuth.signOut()
//            finish()
        } else {
            Toast.makeText(this@HomeActivity, "Erro!", Toast.LENGTH_SHORT).show()
        }
    }
}