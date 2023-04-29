package ifpe.pdm.praticas

import android.R.id
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import ifpe.pdm.praticas.databinding.ActivityHomeBinding
import ifpe.pdm.praticas.model.Message
import ifpe.pdm.praticas.model.User


class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var fbAuth: FirebaseAuth
    private lateinit var authListener: FirebaseAuthListener
    private lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)

        binding.buttonSair.setOnClickListener { this.sair() }

        setContentView(binding.root)

        this.fbAuth = FirebaseAuth.getInstance()
        this.authListener = FirebaseAuthListener(this)

        val fbDB = FirebaseDatabase.getInstance()
        val fbUser = fbAuth.currentUser
        val drUser = fbDB.getReference("users/" + fbUser!!.uid)
        val drChat = fbDB.getReference("chat")
        drUser.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                val tempUser = dataSnapshot.value as HashMap<String, String>
                if (tempUser != null) {
                    this@HomeActivity.user = User(tempUser["name"], tempUser["email"])
                    binding.textWelcome.text = "Welcome " + user.name + "!"
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {}
        })
        drChat.addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                val message = dataSnapshot.value as HashMap<String, String>
                showMessage(Message(message["name"], message["text"]))
            }

            override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onChildRemoved(dataSnapshot: DataSnapshot) {}
            override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {}
            override fun onCancelled(databaseError: DatabaseError) {}
        })

        binding.sendMessage.setOnClickListener {
            val message: String? = binding.editMessage.text.toString()
            val newMessage = Message(user.name, message)
            binding.editMessage.text.clear()
            drChat.push().setValue(newMessage)
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