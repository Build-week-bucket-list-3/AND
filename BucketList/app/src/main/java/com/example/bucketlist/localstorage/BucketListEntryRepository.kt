package com.example.bucketlist.localstorage

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.bucketlist.models.BucketListEntry

class BucketListEntryRepository (application: Application) {

    private var bucketListEntryDao: BucketListEntryDao

    private var allBucketListEntries: LiveData<List<BucketListEntry>>

    private var isOnline = true

    init {
        val database: BucketListDatabase = BucketListDatabase.getInstance(
            application.applicationContext
        )!!

        if (isOnline) {

            //TODO: get Data from API if valid,
            // delete local data, set local data equal to data from API
        }

        bucketListEntryDao = database.bucketListEntryDao()

        allBucketListEntries = bucketListEntryDao.getAllBucketListEntries()
    }

    fun insert(bucketList: BucketListEntry) {
        val insertBucketListEntryAsyncTask = InsertBucketListEntryAsyncTask(bucketListEntryDao).execute(bucketList)
    }

    fun update(bucketList: BucketListEntry) {
        val updateBucketListEntryAsyncTask = UpdateBucketListEntryAsyncTask(bucketListEntryDao).execute(bucketList)
    }

    fun delete(bucketList: BucketListEntry) {
        val deleteBucketListEntryAsyncTask = DeleteBucketListEntryAsyncTask(bucketListEntryDao).execute(bucketList)
    }

    fun deleteAllBucketListEntries() {
        val deleteAllBucketListEntriesAsyncTask = DeleteAllBucketListEntriesAsyncTask(
            bucketListEntryDao
        ).execute()
    }

    fun getAllBucketListEntries(): LiveData<List<BucketListEntry>> {
        return allBucketListEntries
    }


    fun getBucketListEntryById(id: Long): LiveData<BucketListEntry> {
        return bucketListEntryDao.getBucketListEntryById(id)
    }

    fun getBucketListEntriesByBucketListId(id: Long): LiveData<List<BucketListEntry>> {
        return bucketListEntryDao.getBucketListEntriesByBucketListId(id)
    }

    companion object {

        private class InsertBucketListEntryAsyncTask(bucketListEntryDaoIn: BucketListEntryDao) :
            AsyncTask<BucketListEntry, Unit, Unit>() {
            val bucketListEntryDao = bucketListEntryDaoIn

            override fun doInBackground(vararg p0: BucketListEntry?) {
                bucketListEntryDao.insert(p0[0]!!)
            }
        }

        private class UpdateBucketListEntryAsyncTask(bucketListEntryDaoIn: BucketListEntryDao) :
            AsyncTask<BucketListEntry, Unit, Unit>() {
            val bucketListEntryDao = bucketListEntryDaoIn

            override fun doInBackground(vararg p0: BucketListEntry?) {
                bucketListEntryDao.update(p0[0]!!)
            }
        }

        private class DeleteBucketListEntryAsyncTask(bucketListEntryDaoIn: BucketListEntryDao) :
            AsyncTask<BucketListEntry, Unit, Unit>() {
            val bucketListEntryDao = bucketListEntryDaoIn

            override fun doInBackground(vararg p0: BucketListEntry?) {
                bucketListEntryDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllBucketListEntriesAsyncTask(bucketListEntryDaoIn: BucketListEntryDao) :
            AsyncTask<Unit, Unit, Unit>() {
            val bucketListEntryDao = bucketListEntryDaoIn

            override fun doInBackground(vararg p0: Unit?) {
                bucketListEntryDao.deleteAllBucketListEntries()
            }
        }

    }

}