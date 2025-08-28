package io.github.korhenon.data.packages

import androidx.core.graphics.drawable.toBitmap
import io.github.korhenon.data.impl.android.ImagesDataSource
import io.github.korhenon.data.impl.android.PackagesDataSource
import io.github.korhenon.data.impl.room.AppInfoCacheModel
import io.github.korhenon.data.impl.room.CacheDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PackagesRepositoryImpl(
    private val dataSource: PackagesDataSource,
    private val cacheDataSource: CacheDataSource.AppInfoCacheDataSource,
    private val imagesDataSource: ImagesDataSource
) : PackagesRepository {

    override fun loadInstalledApps(): Flow<InstalledApps> = flow {
        val cachedValue = cacheDataSource.read()
        emit(
            InstalledApps(
                isActual = false,
                applications = cachedValue.map {
                    AppInfo(it.label, it.packageName, imagesDataSource.read(it.packageName))
                }
            )
        )
        val currentValue = dataSource.getInstalledApps()
        cacheDataSource.clear()
        cacheDataSource.write(currentValue.map { AppInfoCacheModel(it.label, it.packageName) })
        for (app in currentValue) {
            imagesDataSource.write(app.packageName, app.icon.toBitmap())
        }
        emit(
            InstalledApps(
                isActual = true,
                applications = currentValue.map {
                    AppInfo(
                        it.label,
                        it.packageName,
                        it.icon.toBitmap()
                    )
                }
            )
        )
    }


    override fun launchApp(app: AppInfo) {
        dataSource.launchApp(app.packageName)
    }
}