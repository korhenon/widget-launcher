package io.github.korhenon.data.impl.android

import android.graphics.Bitmap

interface ImagesDataSource {
    fun read(name: String): Bitmap?
    fun write(name: String, bitmap: Bitmap)
}