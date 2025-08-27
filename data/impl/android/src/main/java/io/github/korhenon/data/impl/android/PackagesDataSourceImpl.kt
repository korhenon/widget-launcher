package io.github.korhenon.data.impl.android

import android.content.Context
import android.content.Intent

internal class PackagesDataSourceImpl(
    private val context: Context
) : PackagesDataSource {
    private val packageManager = context.packageManager

    override fun getInstalledApps(): List<AndroidAppInfo> {
        val intent = Intent(Intent.ACTION_MAIN).apply { addCategory(Intent.CATEGORY_LAUNCHER) }
        return packageManager.queryIntentActivities(intent, 0).map {
            val info = it.activityInfo.applicationInfo
            AndroidAppInfo(
                label = packageManager.getApplicationLabel(info).toString(),
                packageName = info.packageName,
                icon = packageManager.getApplicationIcon(info)
            )
        }
    }

    override fun launchApp(packageName: String) {
        context.startActivity(
            packageManager.getLaunchIntentForPackage(packageName)
        )
    }
}