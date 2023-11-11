package com.xlp.flyme.extra.hook.modules.settings.pressUnlock

import android.annotation.SuppressLint
import android.content.Context
import android.os.PowerManager
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean
import de.robv.android.xposed.XposedHelpers

object PressUnlock:YukiBaseHooker() {
    @SuppressLint("WrongConstant")
    override fun onHook() {
        if (getBoolean("press_unlock", false)) {
            "com.android.keyguard.KeyguardUpdateMonitor".toClass()
                .method {
                    name = "isFingerprintDisabled"
                    param(Int::class.java)
                }.hook {
                    before {
                        val mContext =
                            XposedHelpers.getObjectField(this.instance, "mContext") as Context
                        val mPowerManager = mContext.getSystemService("power") as PowerManager
                        this.result = !(mPowerManager.isScreenOn)
                    }
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}