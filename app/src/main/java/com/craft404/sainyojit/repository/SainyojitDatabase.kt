package com.craft404.sainyojit.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.craft404.sainyojit.util.showToast

//@Database(entities = [], version = 1, exportSchema = false)
abstract class SainyojitDatabase : RoomDatabase() {

    companion object {
        private const val DATABASE_NAME = "sainyojit.db"

        fun build(context: Context) {
            showToast("Hello world!")
            Room.databaseBuilder(
                context.applicationContext,
                SainyojitDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
}