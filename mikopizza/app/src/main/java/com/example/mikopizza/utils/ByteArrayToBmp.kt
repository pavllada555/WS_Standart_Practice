package com.example.mikopizza.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory

fun byteArrayToBmp(byteArray: ByteArray): Bitmap {
    return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
}