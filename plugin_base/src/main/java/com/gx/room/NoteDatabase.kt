//package com.gx.room
//
//import android.content.Context
//import androidx.room.Room
//import androidx.room.RoomDatabase
//import androidx.sqlite.db.SupportSQLiteDatabase
//import com.gx.room.RoomConfig.TASK_DB_NAME
//import com.gx.task.database.PlanDao
//import com.gx.task.database.TaskDao
//import com.sn.libaray.log.LogUtils
//
////@Database(entities = [com.gx.task.repository.data.Plan::class, Task::class], version = TASK_DB_VERSION)
//abstract class NoteDatabase : RoomDatabase() {
//
//    abstract fun planDao(): com.gx.task.database.PlanDao?
//
//    abstract fun taskDao(): com.gx.task.database.TaskDao?
//
//
//    companion object {
//        const val TAG = "RoomTaskDataBase"
//
//        @Volatile
//        private var instance: NoteDatabase? = null
//
//        fun getInstance(context: Context): NoteDatabase? {
//            return instance ?: synchronized(this) {
//                instance ?: buildDatabase(context).also {
//                    instance = it
//                }
//            }
//        }
//
//        private fun buildDatabase(appContext: Context): NoteDatabase {
//            return Room.databaseBuilder(
//                appContext,
//                NoteDatabase::class.java,
//                TASK_DB_NAME
//            )
//                .addCallback(object : RoomDatabase.Callback() {
//                    override fun onCreate(db: SupportSQLiteDatabase) {
//                        super.onCreate(db)
//                        LogUtils.v( "onCreate ")
//                    }
//                })
////                .fallbackToDestructiveMigration()
////                .addMigrations(upgradeMigration4_5())
//                .build()
//        }
//    }
//}