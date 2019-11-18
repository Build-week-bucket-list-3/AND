package com.example.bucketlist.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.bucketlist.localstorage.BucketListEntryRepository
import com.example.bucketlist.models.BucketListEntry

class BucketListEntryViewModel (application: Application) : AndroidViewModel(application){
    private var repository: BucketListEntryRepository =
        BucketListEntryRepository(application)
    private var allBucketListEntries: LiveData<List<BucketListEntry>> = repository.getAllBucketListEntries()

    fun insert(bucketList: BucketListEntry) {
        repository.insert(bucketList)
    }

    fun update(bucketList: BucketListEntry) {
        repository.update(bucketList)
    }

    fun delete(bucketList: BucketListEntry) {
        repository.delete(bucketList)
    }

    fun deleteAllBucketListEntries() {
        repository.deleteAllBucketListEntries()
    }

    fun getAllBucketListEntries(): LiveData<List<BucketListEntry>> {
        return allBucketListEntries
    }

    fun getBucketListEntryById(id:Long): LiveData<BucketListEntry> {
        return repository.getBucketListEntryById(id)
    }

    fun getBucketListEntryByBucketListId(id: Long): LiveData<List<BucketListEntry>>{
        return repository.getBucketListEntriesByBucketListId(id)
    }
}