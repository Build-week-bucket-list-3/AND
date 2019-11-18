package com.example.bucketlist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.bucketlist.localstorage.BucketListRepository
import com.example.bucketlist.models.BucketList
import com.example.bucketlist.models.BucketListEntry

class BucketListViewModel (application: Application) : AndroidViewModel(application){
    private var repository: BucketListRepository =
        BucketListRepository(application)
    private var allBucketLists: LiveData<List<BucketList>> = repository.getAllBucketLists()

    fun insert(bucketList: BucketList) {
        repository.insert(bucketList)
    }

    fun update(bucketList: BucketList) {
        repository.update(bucketList)
    }

    fun delete(bucketList: BucketList) {
        repository.delete(bucketList)
    }

    fun deleteAllBucketLists() {
        repository.deleteAllBucketLists()
    }

    fun getAllBucketList(): LiveData<List<BucketList>> {
        return allBucketLists
    }

    fun getBucketListById(id:Long): LiveData<BucketList> {
        return repository.getBucketListsById(id)
    }
}