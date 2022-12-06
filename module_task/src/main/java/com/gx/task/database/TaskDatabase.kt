package com.gx.task.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.gx.task.repository.data.Task
import com.gx.room.RoomConfig.TASK_DB_NAME
import com.gx.room.RoomConfig.TASK_DB_VERSION
import com.gx.task.repository.data.Plan

@Database(entities = [Plan::class, Task::class], version = TASK_DB_VERSION,exportSchema = false)
abstract class TaskDatabase : RoomDatabase() {

    abstract fun planDao(): PlanDao?

    abstract fun taskDao(): TaskDao?


    companion object {
        const val TAG = "TaskDatabase"

        @Volatile
        private var instance: TaskDatabase? = null

        fun getInstance(context: Context): TaskDatabase{
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        private fun buildDatabase(appContext: Context): TaskDatabase {
            return Room.databaseBuilder(
                appContext,
                TaskDatabase::class.java,
                TASK_DB_NAME
            )
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d( TAG,"onCreate()")
                    }
                })
//                .fallbackToDestructiveMigration()
//                .addMigrations(upgradeMigration4_5())
                .build()
        }
    }
}