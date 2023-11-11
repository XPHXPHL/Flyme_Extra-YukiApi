package com.xlp.flyme.extra.hook.modules.settings.cipherDiskVibrator

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean
import de.robv.android.xposed.XposedHelpers

object CipherDiskVibrator : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("cipher_disk_vibrator", false)) {
            "com.meizu.settings.widget.LockDigitView".toClass()
                .method {
                    name = "detectAndAddHit"
                    param(Float::class.java, Float::class.java, Boolean::class.java)
                }.hook {
                    before {
                        val targetObject = this.instance
                        XposedHelpers.setBooleanField(targetObject, "mEnableHapticFeedback", true)
                    }
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}