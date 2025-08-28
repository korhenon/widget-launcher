package io.github.korhenon.data.impl.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
internal interface AppInfoDao {
    @Query("SELECT * FROM apps")
    fun select(): List<AppInfoEntity>

    @Insert
    fun insert(apps: List<AppInfoEntity>)

    @Query("DELETE FROM apps")
    fun deleteAll()
}