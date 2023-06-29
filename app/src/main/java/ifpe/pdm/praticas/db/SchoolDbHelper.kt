import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import ifpe.pdm.praticas.db.SchoolContract.DATABASE_NAME
import ifpe.pdm.praticas.db.SchoolContract.DATABASE_VERSION
import ifpe.pdm.praticas.db.SchoolContract.SQL_CREATE_STUDENT
import ifpe.pdm.praticas.db.SchoolContract.SQL_DELETE_STUDENT

class SchoolDbHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(SQL_CREATE_STUDENT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL(SQL_DELETE_STUDENT)
        onCreate(db)
    }
}
