package com.xlp.flyme.extra.hook.modules.systemUI.vibrator.fingerprint

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.XUtils

object FingerprintVibrator : YukiBaseHooker() {
    override fun onHook() {
        "com.android.keyguard.KeyguardUpdateMonitor".toClass()
            .method {
                name = "onFingerprintAuthenticated"
            }.hook {
                after {
                    XUtils.vibratorHelper(31021)
                }
            }.result {
                onAllFailure {
                    YLog.error(msg = "${it.message}", tag = tag)
                }
            }
    }
}