package com.example.githuprepo.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.githuprepo.data.local.convertors.RepositoryModelConvertor
import com.example.githuprepo.data.local.model.RepositoryModelEntity


@Database(entities = [RepositoryModelEntity::class], version = 2, exportSchema = false)
@TypeConverters(RepositoryModelConvertor::class)
abstract class RepositoriesDatabase: RoomDatabase() {

    companion object{

        private const val DATABASE_NAME = "repositories.db"

        @Volatile
        private var INSTANCE: RepositoriesDatabase? = null

        private fun create(context: Context) : RepositoriesDatabase =
            Room.databaseBuilder(context,RepositoriesDatabase::class.java,DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()


        fun getInstance(context: Context): RepositoriesDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = create(context)
                }
                INSTANCE = instance
                return instance
            }
        }
    }


    abstract fun getRepositoriesDao(): RepositoriesDao

}