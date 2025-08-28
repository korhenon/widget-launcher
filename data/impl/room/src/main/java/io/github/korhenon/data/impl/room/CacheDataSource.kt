package io.github.korhenon.data.impl.room

sealed interface CacheDataSource<T> {
    suspend fun read(): T
    suspend fun write(value: T)
    suspend fun clear()

    interface AppInfoCacheDataSource : CacheDataSource<List<AppInfoCacheModel>>
}