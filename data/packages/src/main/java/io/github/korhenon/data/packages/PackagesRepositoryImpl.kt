package io.github.korhenon.data.packages

import androidx.core.graphics.drawable.toBitmap
import io.github.korhenon.data.impl.android.PackagesDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class PackagesRepositoryImpl(
    private val dataSource: PackagesDataSource
) : PackagesRepository {
    override suspend fun loadInstalledApps(): InstalledApps = withContext(Dispatchers.IO) {
        return@withContext InstalledApps(
            dataSource.getInstalledApps().map {
                AppInfo(
                    label = it.label,
                    packageName = it.packageName,
                    icon = it.icon.toBitmap()
                )
            }
        )
    }


    override fun launchApp(app: AppInfo) {
        dataSource.launchApp(app.packageName)
    }
}