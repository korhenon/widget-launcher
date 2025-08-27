package io.github.korhenon.data.impl.android

interface PackagesDataSource {
    fun getInstalledApps(): List<AndroidAppInfo>

    fun launchApp(packageName: String)
}