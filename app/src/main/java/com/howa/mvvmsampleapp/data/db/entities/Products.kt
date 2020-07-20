package com.howa.mvvmsampleapp.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Products(
    @PrimaryKey(autoGenerate = false) var id: Int,
    var product_name: String?,
    var supplier_name: String?,
    var notes: String?,
    var created_by: String?,
    var product_type: Int,
    var quantity: Int
)