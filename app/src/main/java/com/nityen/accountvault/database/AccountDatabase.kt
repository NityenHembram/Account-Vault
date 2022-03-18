package com.nityen.accountvault.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nityen.accountvault.dao.AccountDao
import com.nityen.accountvault.model.Accounts

@Database(entities = [Accounts::class], version = 1, exportSchema = false)
abstract class AccountDatabase :RoomDatabase(){
    abstract fun accDao():AccountDao

    companion object{
        private var INSTANCE:AccountDatabase? = null

        fun getInstance(context:Context): AccountDatabase{
            return INSTANCE ?: synchronized(this){
                val ins = Room.databaseBuilder(context.applicationContext,AccountDatabase::class.java
                ,"account_database").build()
                INSTANCE = ins
                ins
            }
        }


//        fun getDatabase(context: Context): AccountDatabase {
//            // if the INSTANCE is not null, then return it,
//            // if it is, then create the database
//            return INSTANCE ?: synchronized(this) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AccountDatabase::class.java,
//                    "word_database"
//                ).build()
//                INSTANCE = instance
//                // return instance
//                instance
//            }
//        }
    }
}