package com.android.pos.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class MasterDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var Instance: MasterDatabase? = null

        fun getDatabase(ctx: Context): MasterDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(ctx, MasterDatabase::class.java, "master_database").build()
                    .also { db -> Instance = db }
            }
        }
    }
}