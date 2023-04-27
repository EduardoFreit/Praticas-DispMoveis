package ifpe.pdm.praticas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ifpe.pdm.praticas.databinding.ActivityHomeBinding


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
    }
    override fun onStart() {
        super.onStart()
        fbAuth.addAuthStateListener(authListener)
    }
    override fun onStop() {
        super.onStop()
        fbAuth.removeAuthStateListener(authListener)
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