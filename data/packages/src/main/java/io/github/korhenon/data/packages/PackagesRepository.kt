package io.github.korhenon.data.packages

import kotlinx.coroutines.flow.Flow

interface PackagesRepository {
    fun loadInstalledApps(): Flow<InstalledApps>

    fun launchApp(app: AppInfo)
}