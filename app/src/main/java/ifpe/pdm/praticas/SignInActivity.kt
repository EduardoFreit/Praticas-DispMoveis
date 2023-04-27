package ifpe.pdm.praticas

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import ifpe.pdm.praticas.databinding.ActivitySignInBinding


class SignInActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)

        binding.buttonCadastrar.setOnClickListener { this.irParaCadastrar() }

        binding.buttonLogin.setOnClickListener { this.buttonSignInClick(binding.root) }

        setContentView(binding.root)
    }

    private fun irParaCadastrar() {
        val intent: Intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun buttonSignInClick(view: View) {
        val login: String = binding.editEmail.text.toString()
        val passwd: String = binding.editPassword.text.toString()
        val mAuth = FirebaseAuth.getInstance()
        mAuth.signInWithEmailAndPassword(login, passwd)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                val msg = if (task.isSuccessful) "SIGN IN OK!" else "SIGN IN ERROR!"
                Toast.makeText(
                    this@SignInActivity, msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
    }

}