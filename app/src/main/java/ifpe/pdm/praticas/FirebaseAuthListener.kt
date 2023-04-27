package ifpe.pdm.praticas

import android.app.Activity
import android.content.Intent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener


class FirebaseAuthListener(private val activity: Activity) : AuthStateListener {
    override fun onAuthStateChanged(firebaseAuth: FirebaseAuth) {
        val user = firebaseAuth.currentUser
        var intent: Intent? = null

        if (user != null && activity !is HomeActivity) {
            intent = Intent(activity, HomeActivity::class.java)
        }

        if (user == null && activity is HomeActivity) {
            intent = Intent(activity, SignInActivity::class.java)
        }

        if (intent != null) {
            activity.startActivity(intent)
            activity.finish()
        }
    }
}