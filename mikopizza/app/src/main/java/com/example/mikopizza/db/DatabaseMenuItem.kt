package com.example.mikopizza.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.mikopizza.network.models.menuorders.MenuResponseModel
import com.example.mikopizza.utils.Converters
import java.math.BigDecimal


@Entity(primaryKeys = ["name", "category"], tableName = "menu")
data class DatabaseMenuItem(
    val name: String,
    val price: BigDecimal,
    val image: ByteArray,
    val category: Int
)

fun List<DatabaseMenuItem>.asDomainModel(): List<MenuResponseModel> {
    return map {
        MenuResponseModel(
            name = it.name,
            price = it.price,
            image = it.image,
            category = it.category
        )
    }
}

@Dao
interface MenuDao {
    @Query("SELECT * FROM menu")
    fun getMenu(): LiveData<List<DatabaseMenuItem>>

    @Query("SELECT * FROM menu")
    fun getAll(): List<DatabaseMenuItem>

    @Query("SELECT * FROM menu WHERE name LIKE :name")
    fun findByName(name: String): DatabaseMenuItem

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<DatabaseMenuItem>)

    @Delete
    fun delete(menuItem: DatabaseMenuItem)

}

@Database(entities = [DatabaseMenuItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun menuDao(): MenuDao

    companion object {

        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "product_database"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}