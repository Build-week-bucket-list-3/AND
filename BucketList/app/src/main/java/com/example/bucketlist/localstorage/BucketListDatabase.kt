package com.example.bucketlist.localstorage

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.bucketlist.models.BucketList
import com.example.bucketlist.models.BucketListEntry

@Database(entities = [BucketList::class, BucketListEntry::class], exportSchema = true, version = 5)
abstract class BucketListDatabase : RoomDatabase() {

    abstract fun bucketListDao(): BucketListDao
    abstract fun bucketListEntryDao(): BucketListEntryDao

    companion object {
        private var instance: BucketListDatabase? = null

        fun getInstance(context: Context): BucketListDatabase? {
            if (instance == null) {
                synchronized(BucketListDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BucketListDatabase::class.java, "user_database"
                    )
                        .fallbackToDestructiveMigration() // when version increments, it migrates (deletes db and creates new) - else it crashes
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance
        }

        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                PopulateDbAsyncTask(instance)
                    .execute()
            }
        }
    }

    //Mock Data here
    class PopulateDbAsyncTask(db: BucketListDatabase?) : AsyncTask<Unit, Unit, Unit>() {
        private val bucketListDao = db?.bucketListDao()
        private val bucketListEntryDao = db?.bucketListEntryDao()

        override fun doInBackground(vararg p0: Unit?) {
            //TODO : put mock data here
        }
    }
}