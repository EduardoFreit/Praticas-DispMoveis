package ifpe.pdm.praticas.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface StudentDAO {
    @Query("SELECT * FROM student")
    fun getAll(): List<Student>

    @Query("SELECT * FROM student WHERE id IN (:studentIds)")
    fun loadAllByIds(studentIds: IntArray): List<Student>

    @Query("SELECT * FROM student WHERE name = :name LIMIT 1")
    fun findByName(name: String): Student

    @Insert
    fun insertAll(vararg student: Student)

    @Update
    fun update(student: Student)

    @Delete
    fun delete(student: Student)
}
