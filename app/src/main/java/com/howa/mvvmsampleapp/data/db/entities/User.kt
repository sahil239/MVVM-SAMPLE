package com.howa.mvvmsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    var user_id: Int? = null,
    var name: String? = null,
    var designation: String? = null,
    var profile_photo: String? = null,
    var email: String? = null,
    var phone_number: String? = null,
    var is_admin: Int? = null

) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}