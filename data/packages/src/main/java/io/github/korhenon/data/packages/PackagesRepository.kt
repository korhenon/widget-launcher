package io.github.korhenon.data.packages

interface PackagesRepository {
    suspend fun loadInstalledApps(): InstalledApps

    fun launchApp(app: AppInfo)
}