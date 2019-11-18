package com.example.bucketlist.localstorage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bucketlist.models.BucketList
import com.example.bucketlist.models.BucketListEntry

@Dao
interface BucketListEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bucketListEntry: BucketListEntry)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(bucketListEntry:BucketListEntry)

    @Delete
    fun delete(bucketListEntry: BucketListEntry)

    @Query("DELETE FROM entries")
    fun deleteAllBucketListEntries()

    @Query("SELECT * FROM entries")
    fun getAllBucketListEntries(): LiveData<List<BucketListEntry>>

    @Query("SELECT * FROM entries WHERE entry_id IS :id")
    fun getBucketListEntryById(id: Long): LiveData<BucketListEntry>


    @Query("SELECT * FROM entries WHERE bucket_list_id IS :id")
    fun getBucketListEntriesByBucketListId(id: Long): LiveData<List<BucketListEntry>>
}