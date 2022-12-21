package com.gx.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

fun upgradeMigration4_5(): Migration {
    return object:Migration(4, 5){
        override fun migrate(database: SupportSQLiteDatabase) {
//            database.execSQL("ALTER TABLE task ADD COLUMN taskStatus INTEGER NOT NULL DEFAULT 0")
        }

    }
}