package com.nityen.accountvault.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Accounts(
    @ColumnInfo var account_name: String,
    @ColumnInfo var account_id: String,
    @ColumnInfo var account_pass: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


}