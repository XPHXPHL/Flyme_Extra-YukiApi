package com.xlp.flyme.extra.utils

import android.annotation.SuppressLint
import android.app.AndroidAppHelper
import android.app.Service
import android.os.VibrationEffect
import android.os.Vibrator
import java.io.DataOutputStream

@SuppressLint("WrongConstant", "NewApi")
object XUtils {
    fun vibratorHelper(effectId: Int) {
        val mVibrator =
            AndroidAppHelper.currentApplication().applicationContext.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        mVibrator.vibrate(VibrationEffect.createPredefined(effectId))
    }

    fun execShell(command: String) {
        try {
            val p = Runtime.getRuntime().exec("su")
            val outputStream = p.outputStream
            val dataOutputStream = DataOutputStream(outputStream)
            dataOutputStream.writeBytes(command)
            dataOutputStream.flush()
            dataOutputStream.close()
            outputStream.close()
        } catch (t: Throwable) {
            t.printStackTrace()
        }
    }

    fun execShell(commands: Array<String>): String {
        val stringBuilder = java.lang.StringBuilder()
        for (command in commands) {
            stringBuilder.append(execShell(command))
            stringBuilder.append("\n")
        }
        return stringBuilder.toString()
    }

}
