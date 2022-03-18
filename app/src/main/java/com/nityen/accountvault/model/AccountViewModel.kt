package com.nityen.accountvault.model

import android.accounts.Account
import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.nityen.accountvault.dao.AccountDao
import com.nityen.accountvault.database.AccountDatabase
import com.nityen.accountvault.repo.AccountRepository
import kotlinx.coroutines.launch

class AccountViewModel(application: Application) : AndroidViewModel(application) {
    val allData : LiveData<List<Accounts>>
    var repo:AccountRepository
    init {

        val dao = AccountDatabase.getInstance(application).accDao()
         repo = AccountRepository(dao)
        allData = dao.getAll()
    }

//
    suspend fun insert(acc: Accounts) = viewModelScope.launch { repo.insert(acc) }
    suspend fun delete(acc: Accounts) =viewModelScope.launch  { repo.delete(acc) }
    suspend fun update(acc: Accounts) = viewModelScope.launch { repo.update(acc) }
    suspend fun update1(id:Int,name:String,email:String,pass:String) = viewModelScope.launch { repo.update1(id,name,email,pass) }


}