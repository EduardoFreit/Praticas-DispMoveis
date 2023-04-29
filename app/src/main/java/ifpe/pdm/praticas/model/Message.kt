package ifpe.pdm.praticas.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
class Message (val name: String? = null, val text: String? = null)