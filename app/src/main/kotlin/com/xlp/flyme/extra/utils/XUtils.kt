package com.xlp.flyme.extra.utils

import android.annotation.SuppressLint
import android.app.AndroidAppHelper
import android.app.Service
import android.os.VibrationEffect
import android.os.Vibrator
import cn.fkj233.ui.activity.MIUIActivity
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import java.io.DataOutputStream
import java.io.IOException
import java.io.OutputStream
import java.nio.file.Files
import java.nio.file.OpenOption
import java.nio.file.Paths

@SuppressLint("WrongConstant", "NewApi")
object XUtils {
    @Suppress("DEPRECATION")
    fun vibratorHelper(effectId: Int) {
        val mVibrator =
            AndroidAppHelper.currentApplication().applicationContext.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
        mVibrator.vibrate(VibrationEffect.createPredefined(effectId))
    }

    @Suppress("DEPRECATION")
    @SuppressLint("ServiceCast")
    fun perfVibratorHelper(effectId: Int) {
        val mVibrator =
            MIUIActivity.context.applicationContext.getSystemService(Service.VIBRATOR_SERVICE) as Vibrator
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

    fun writeNode(path: String, mode: String) {
        var fos: OutputStream? = null
        try {
            try {
                try {
                    fos = Files.newOutputStream(
                        Paths.get(path, *arrayOfNulls<String>(0)),
                        *arrayOfNulls<OpenOption>(0)
                    )
                    fos.write(java.lang.String.valueOf(mode).toByteArray(charset("US-ASCII")))
                } catch (e: IOException) {
                    YLog.error(tag = tag, msg = "${e.message}")
                    e.printStackTrace()
                    if (fos == null) {
                        return
                    }
                    fos.close()
                }
                fos?.close()
            } catch (th: Throwable) {
                if (fos != null) {
                    try {
                        fos.close()
                    } catch (_: IOException) {
                    }
                }
                throw th
            }
        } catch (_: IOException) {

        }
    }

}
