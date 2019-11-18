package com.example.bucketlist.models

import androidx.room.*

@Entity(tableName = "bucket_lists")
data class BucketList(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "bucket_list_id")
    var bucket_list_id: Long = 0
)


@Entity(
    tableName = "entries", foreignKeys = [ForeignKey(
        entity = BucketList::class,
        parentColumns = arrayOf("bucket_list_id"),
        childColumns = arrayOf("entry_id")
    )]
)
data class BucketListEntry(
    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "location")
    var location: String = "",

    @ColumnInfo(name = "desc")
    var desc: String = "",

    @ColumnInfo(name = "lat")
    var lat: Double = 0.0,

    @ColumnInfo(name = "lon")
    var lon: Double = 0.0,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "entry_id")
    var entry_id: Long = 0,

    @ColumnInfo(name = "bucket_list_id")
    var bucketListId: Long
)