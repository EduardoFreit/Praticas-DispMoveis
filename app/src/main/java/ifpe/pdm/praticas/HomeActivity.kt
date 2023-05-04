package ifpe.pdm.praticas

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import ifpe.pdm.praticas.databinding.ActivityHomeBinding
import ifpe.pdm.praticas.model.Message
import ifpe.pdm.praticas.model.User


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var fbAuth: FirebaseAuth
    private lateinit var authListener: FirebaseAuthListener
    private lateinit var user: User
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.buttonSair.setOnClickListener { this.sair() }

        setContentView(binding.root)

        this.fbAuth = FirebaseAuth.getInstance()
        this.authListener = FirebaseAuthListener(this)

        val fbUser: FirebaseUser? = fbAuth.currentUser
        val drUser: DocumentReference = db.collection("users").document(fbUser!!.uid)
        val drChat: CollectionReference = db.collection("chat")

        drUser.addSnapshotListener { value, _ ->
            value?.let {
                val tempUser: User? = it.toObject(User::class.java)
                tempUser?.let {
                    user = it
                    binding.textWelcome.text = "Welcome " + it.name + "!"
                }
            }
        }

        drChat.addSnapshotListener { value, _ ->
            value?.documentChanges?.let {
                for (dc: DocumentChange in it) {
                    if (dc.type == DocumentChange.Type.ADDED) {
                        val message: Message = dc.document.toObject(Message::class.java)
                        this.showMessage(message)
                    }
                }
            }
        }

        binding.sendMessage.setOnClickListener {
            val message: String = binding.editMessage.text.toString()
            val newMessage = Message(user.name, message)
            binding.editMessage.text.clear()
            drChat.document().set(newMessage)
        }
        binding.editMessage.setOnClickListener { this.myEnter() }

    }

    private fun myEnter() {
        binding.editMessage.setOnKeyListener (View.OnKeyListener{ view, keyCode, keyEvent ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
                binding.sendMessage.performClick()
                return@OnKeyListener true
            }
            false
        })
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