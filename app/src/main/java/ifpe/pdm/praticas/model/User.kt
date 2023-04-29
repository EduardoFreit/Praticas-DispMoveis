package ifpe.pdm.praticas.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class User(val name: String? = null, val email: String? = null)