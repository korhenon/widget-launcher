package io.github.korhenon.data.impl.room

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class AppInfoCacheDataSourceImpl (
    private val dao: AppInfoDao
) : CacheDataSource.AppInfoCacheDataSource {
    override suspend fun read(): List<AppInfoCacheModel> = withContext(Dispatchers.IO) {
        return@withContext dao.select().map { it.toCacheModel() }
    }

    override suspend fun clear() = withContext(Dispatchers.IO) {
        dao.deleteAll()
    }

    override suspend fun write(value: List<AppInfoCacheModel>) = withContext(Dispatchers.IO) {
        dao.insert(value.map { it.toEntity() })
    }

}