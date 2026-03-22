package br.com.evelynrodrigues.receitasapp.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.evelynrodrigues.receitasapp.data.local.dao.ReceitaDao
import br.com.evelynrodrigues.receitasapp.data.local.entity.ReceitaEntity

@Database(
    entities = [ReceitaEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun receitaDao(): ReceitaDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room
                    .databaseBuilder(
                        context = context,
                        klass = AppDatabase::class.java,
                        name = "receitas"
                    )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }

}