package com.nityen.accountvault.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nityen.accountvault.model.Accounts


@Dao
interface AccountDao {

    @Query("SELECT * FROM Accounts order by id ASC")
    fun getAll():LiveData<List<Accounts>>

    @Insert
    suspend fun insert(acc:Accounts)

    @Delete
    suspend fun delete(acc:Accounts)

    @Update
    suspend fun update(acc:Accounts)

    @Query("Update Accounts Set account_name =:name , account_id =:email,account_pass=:pass Where id=:id")
    suspend fun update1(id:Int,name:String,email:String,pass:String)

}