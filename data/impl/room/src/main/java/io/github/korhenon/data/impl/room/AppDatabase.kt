package io.github.korhenon.data.impl.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [AppInfoEntity::class], version = 1)
internal abstract class AppDatabase : RoomDatabase(){
    abstract fun AppInfoDao(): AppInfoDao
}