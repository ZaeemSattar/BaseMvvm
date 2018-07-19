package com.ctandem.basemvvm.service.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable

/**
 * Created by Zaeem Sattar on 7/6/2018.
 */
@Entity(tableName = "post")
class Post() : Parcelable {

    var userId: Long = 0
    @PrimaryKey
    var id: Long = 0
    var title = ""
    var body = ""

    constructor(parcel: Parcel) : this() {
        userId = parcel.readLong()
        id = parcel.readLong()
        title = parcel.readString()
        body = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(userId)
        parcel.writeLong(id)
        parcel.writeString(title)
        parcel.writeString(body)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Post> {
        override fun createFromParcel(parcel: Parcel): Post {
            return Post(parcel)
        }

        override fun newArray(size: Int): Array<Post?> {
            return arrayOfNulls(size)
        }
    }


}