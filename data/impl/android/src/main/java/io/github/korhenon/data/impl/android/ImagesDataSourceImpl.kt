package io.github.korhenon.data.impl.android

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File

internal class ImagesDataSourceImpl(
    private val context: Context
) : ImagesDataSource {
    override fun read(name: String): Bitmap? {
        val file = File(context.filesDir, name)
        return if (file.exists()) BitmapFactory.decodeFile(file.path) else null
    }

    override fun write(name: String, bitmap: Bitmap) {
        val file = File(context.filesDir, name)
        val stream = file.outputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
        stream.close()
    }
}