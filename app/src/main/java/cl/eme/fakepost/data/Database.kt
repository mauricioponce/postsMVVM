package cl.eme.fakepost.data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.*

// POJO
@Entity(tableName = "entity_post")
data class PostEntity(val userId: Int, @PrimaryKey val id: Int, val title: String, val body: String)

// Interfaz de operaciones
@Dao
interface PostDao {
   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(postEntities: List<PostEntity>)

   @Query("SELECT * FROM entity_post")
   fun getPosts(): LiveData<List<PostEntity>>
}

// Cliente de base de datos

@Database(entities = [PostEntity::class], version = 1)
abstract class PostDatabase: RoomDatabase() {
    abstract fun postDao(): PostDao
}

class PostApplication : Application() {
    companion object {
        var postDatabase : PostDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()

        postDatabase = Room.databaseBuilder(this, PostDatabase::class.java, "post_db").build()
    }
}
