package com.xlp.flyme.extra.utils

import android.annotation.SuppressLint
import android.app.AndroidAppHelper
import android.app.Service
import android.os.VibrationEffect
import android.os.Vibrator

@SuppressLint("WrongConstant", "NewApi")
object XUtils {
    fun vibratorHelper(effectId: Int) {
        val mVibrator =
            AndroidAppHelper.currentApplication().applicationContext.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        mVibrator.vibrate(VibrationEffect.createPredefined(effectId))
    }
}
