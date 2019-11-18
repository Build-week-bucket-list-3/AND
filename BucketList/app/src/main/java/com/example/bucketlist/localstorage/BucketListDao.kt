package com.example.bucketlist.localstorage

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.bucketlist.models.BucketList

@Dao
interface BucketListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(bucketList: BucketList)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(bucketList:BucketList)

    @Delete
    fun delete(bucketList: BucketList)

    @Query("DELETE FROM bucket_lists")
    fun deleteAllBucketLists()

    @Query("SELECT * FROM bucket_lists")
    fun getAllBucketLists(): LiveData<List<BucketList>>

    @Query("SELECT * FROM bucket_lists WHERE bucket_list_id IS :id")
    fun getBucketListById(id: Long): LiveData<BucketList>
}