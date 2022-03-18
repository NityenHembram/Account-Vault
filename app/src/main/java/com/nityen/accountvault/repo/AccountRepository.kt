package com.nityen.accountvault.repo

import android.accounts.Account
import android.content.Context
import androidx.lifecycle.LiveData
import com.nityen.accountvault.dao.AccountDao
import com.nityen.accountvault.dao.AccountDao_Impl
import com.nityen.accountvault.database.AccountDatabase
import com.nityen.accountvault.model.Accounts

class AccountRepository(private val dao:AccountDao) {
    var allData: LiveData<List<Accounts>>? = dao.getAll()

    suspend fun insert(acc:Accounts) = dao.insert(acc)

    suspend fun delete(acc:Accounts) = dao.delete(acc)

    suspend fun update(acc:Accounts) = dao.update(acc)

    suspend fun update1(id:Int,name:String,email:String,pass:String) = dao.update1(id,name,email,pass)
}