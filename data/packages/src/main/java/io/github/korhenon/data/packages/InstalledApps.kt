package io.github.korhenon.data.packages

data class InstalledApps(
    val isActual: Boolean,
    val applications: List<AppInfo>
)
