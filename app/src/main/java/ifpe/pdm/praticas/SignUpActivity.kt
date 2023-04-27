package ifpe.pdm.praticas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import ifpe.pdm.praticas.databinding.ActivitySignUpBinding


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        binding.buttonCadastrar.setOnClickListener { this.buttonSignUpClick(binding.root) }
        setContentView(binding.root)
    }

    private fun buttonSignUpClick(view: View) {
        val email: String = binding.editEmail.text.toString(),
        val password: String = binding.editPassword.text.toString()
        val mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                val msg = if (task.isSuccessful) "SIGN UP OK!" else "SIGN UP ERROR!"
                Toast.makeText(
                    this@SignUpActivity, msg,
                    Toast.LENGTH_SHORT
                ).show()
            }
    }
}