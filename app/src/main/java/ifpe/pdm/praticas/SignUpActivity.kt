package ifpe.pdm.praticas

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import ifpe.pdm.praticas.databinding.ActivitySignUpBinding
import ifpe.pdm.praticas.model.User


class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var fbAuth: FirebaseAuth
    private lateinit var authListener: FirebaseAuthListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

        binding.buttonCadastrar.setOnClickListener { this.buttonSignUpClick(binding.root) }
        setContentView(binding.root)

        this.fbAuth = FirebaseAuth.getInstance();
        this.authListener = FirebaseAuthListener(this);
    }
    override fun onStart() {
        super.onStart()
        fbAuth.addAuthStateListener(authListener)
    }
    override fun onStop() {
        super.onStop()
        fbAuth.removeAuthStateListener(authListener)
    }
    private fun buttonSignUpClick(view: View) {
        val name: String = binding.editName.text.toString()
        val email: String = binding.editEmail.text.toString()
        val password: String = binding.editPassword.text.toString()
        val mAuth = FirebaseAuth.getInstance()
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task: Task<AuthResult?> ->
                val msg = if (task.isSuccessful) "SIGN UP OK!" else "SIGN UP ERROR!"
                Toast.makeText(
                    this@SignUpActivity, msg,
                    Toast.LENGTH_SHORT
                ).show()

                if(task.isSuccessful) {
                    var tempUser = User(name, email)
                    val drUsers: DatabaseReference = FirebaseDatabase.getInstance().getReference("users")
                    mAuth.currentUser?.let { drUsers.child(it.uid).setValue(tempUser) }
                }
            }
    }
}