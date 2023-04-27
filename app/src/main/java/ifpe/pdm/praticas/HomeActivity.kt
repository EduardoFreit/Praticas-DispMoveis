package ifpe.pdm.praticas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import ifpe.pdm.praticas.databinding.ActivityHomeBinding


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.buttonSair.setOnClickListener { this.sair() }

        setContentView(binding.root)
    }

    private fun sair() {
        val mAuth = FirebaseAuth.getInstance()
        val user: FirebaseUser? = mAuth.currentUser
        if (user != null) {
            mAuth.signOut()
            finish()
        } else {
            Toast.makeText(this@HomeActivity, "Erro!", Toast.LENGTH_SHORT).show()
        }
    }
}