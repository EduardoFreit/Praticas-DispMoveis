package ifpe.pdm.praticas.db

import android.provider.BaseColumns


object SchoolContract {
    const val DATABASE_NAME = "School.db"
    const val DATABASE_VERSION = 1
    const val SQL_CREATE_STUDENT = "CREATE TABLE " + Student.TABLE_NAME + " (" +
            BaseColumns._ID + " INTEGER PRIMARY KEY, " +
            Student.COLUMN_NAME_STUDENT_NAME + " TEXT, " +
            Student.COLUMN_NAME_STUDENT_GRADE + " INTEGER " + ")"
    const val SQL_DELETE_STUDENT = "" +
            "DROP TABLE IF EXISTS " + Student.TABLE_NAME

    object Student : BaseColumns {
        const val TABLE_NAME = "Student"
        const val COLUMN_NAME_STUDENT_NAME = "Name"
        const val COLUMN_NAME_STUDENT_GRADE = "Grade"
    }
}
