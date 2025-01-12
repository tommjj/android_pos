package com.android.pos.data

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [Category::class, Product::class, Order::class, OrderProduct::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class POSDatabase : RoomDatabase() {
    abstract fun categoryDao(): CategoryDao

    abstract fun productDao(): ProductDao

    abstract fun orderDao(): OrderDao

    companion object {
        @Volatile
        private var Instance: POSDatabase? = null

        fun getDatabase(ctx: Context, name: String): POSDatabase {
            Log.d("POSDatabase", "getDatabase: $name")

            return Instance ?: synchronized(this) {
                Room.databaseBuilder(ctx, POSDatabase::class.java, "master_${name}_database")
                    .build()
                    .also { db -> Instance = db }
            }
        }
    }
}