package com.craft404.sainyojit.repository

import android.content.Context
import androidx.room.*
import com.craft404.sainyojit.repository.entity.TicketEntity
import com.craft404.sainyojit.util.showToast
import java.util.*
//
//@Database(
//    entities = [
//        TicketEntity::class,
//
//    ], version = 1, exportSchema = false
//)
//@TypeConverters(DateConverter::class)
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

class DateConverter {
    @TypeConverter
    fun toDate(dateLong: Long?): Date? {
        return if (dateLong == null) null else Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date?): Long? {
        return (date?.time)
    }
}