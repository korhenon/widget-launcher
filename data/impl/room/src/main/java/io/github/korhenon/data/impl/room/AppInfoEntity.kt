package io.github.korhenon.data.impl.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "apps")
internal class AppInfoEntity(
    var label: String,
    var packageName: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

}
