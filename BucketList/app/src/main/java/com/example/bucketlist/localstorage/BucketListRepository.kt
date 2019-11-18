package com.example.bucketlist.localstorage

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.example.bucketlist.models.BucketList

class BucketListRepository (application: Application) {

    private var bucketListDao: BucketListDao

    private var allBucketLists: LiveData<List<BucketList>>

    private var isOnline = true

    init {
        val database: BucketListDatabase = BucketListDatabase.getInstance(
            application.applicationContext
        )!!

        if (isOnline) {

            //TODO: get Data from API if valid,
            // delete local data, set local data equal to data from API
        }

        bucketListDao = database.bucketListDao()

        allBucketLists = bucketListDao.getAllBucketLists()
    }

    fun insert(bucketList: BucketList) {
        val insertBucketListAsyncTask = InsertBucketListAsyncTask(bucketListDao).execute(bucketList)
    }

    fun update(bucketList: BucketList) {
        val updateBucketListAsyncTask = UpdateBucketListAsyncTask(bucketListDao).execute(bucketList)
    }

    fun delete(bucketList: BucketList) {
        val deleteBucketListAsyncTask = DeleteBucketListAsyncTask(bucketListDao).execute(bucketList)
    }

    fun deleteAllBucketLists() {
        val deleteAllBucketListsAsyncTask = DeleteAllBucketListsAsyncTask(
            bucketListDao
        ).execute()
    }

    fun getAllBucketLists(): LiveData<List<BucketList>> {
        return allBucketLists
    }


    fun getBucketListsById(id:Long): LiveData<BucketList> {
        return bucketListDao.getBucketListById(id)
    }

    companion object {

        private class InsertBucketListAsyncTask(bucketListDaoIn: BucketListDao) :
            AsyncTask<BucketList, Unit, Unit>() {
            val bucketListDao = bucketListDaoIn

            override fun doInBackground(vararg p0: BucketList?) {
                bucketListDao.insert(p0[0]!!)
            }
        }

        private class UpdateBucketListAsyncTask(bucketListDaoIn: BucketListDao) :
            AsyncTask<BucketList, Unit, Unit>() {
            val bucketListDao = bucketListDaoIn

            override fun doInBackground(vararg p0: BucketList?) {
                bucketListDao.update(p0[0]!!)
            }
        }

        private class DeleteBucketListAsyncTask(bucketListDaoIn: BucketListDao) :
            AsyncTask<BucketList, Unit, Unit>() {
            val bucketListDao = bucketListDaoIn

            override fun doInBackground(vararg p0: BucketList?) {
                bucketListDao.delete(p0[0]!!)
            }
        }

        private class DeleteAllBucketListsAsyncTask(bucketListDaoIn: BucketListDao) :
            AsyncTask<Unit, Unit, Unit>() {
            val bucketListDao = bucketListDaoIn

            override fun doInBackground(vararg p0: Unit?) {
                bucketListDao.deleteAllBucketLists()
            }
        }

    }

}