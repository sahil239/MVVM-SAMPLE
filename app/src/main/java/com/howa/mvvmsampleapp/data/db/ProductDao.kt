package com.howa.mvvmsampleapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.howa.mvvmsampleapp.data.db.entities.Products

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllProducts(products: List<Products>)

    @Query("SELECT * from Products")
    fun getProducts(): LiveData<List<Products>>

}