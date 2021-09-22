package com.discover.simple.common

import android.webkit.MimeTypeMap

object FileUtil {
    fun getExtension(url: String): String {
        val extension = MimeTypeMap.getFileExtensionFromUrl(url)
        return if (extension.isEmpty()) "" else extension
    }
}