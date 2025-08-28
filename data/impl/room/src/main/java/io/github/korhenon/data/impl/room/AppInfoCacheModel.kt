package io.github.korhenon.data.impl.room

data class AppInfoCacheModel(
    val label: String,
    val packageName: String
)

internal fun AppInfoCacheModel.toEntity(): AppInfoEntity {
    return AppInfoEntity(label, packageName)
}

internal fun AppInfoEntity.toCacheModel(): AppInfoCacheModel {
    return AppInfoCacheModel(label, packageName)
}