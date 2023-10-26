package com.xlp.flyme.extra.hook.modules.systemUI.vibrator.fingerprint

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.XUtils
import com.xlp.flyme.extra.utils.getBoolean
import com.xlp.flyme.extra.utils.getInt

object FingerprintVibrator : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("vibrator_fingerprint", false)) {
            "com.android.keyguard.KeyguardUpdateMonitor".toClass()
                .method {
                    name = "onFingerprintAuthenticated"
                }.hook {
                    after {
                        val effectId = getInt("vibrator_effect_id_fingerprint", 31021)
                        XUtils.vibratorHelper(effectId)
                        YLog.debug(tag = tag, msg = "Fingerprint vibrator effectID is $effectId")
                    }
                }.result {
                    onAllFailure {
                        YLog.error(tag = tag, e = it)
                    }
                }
        }
    }
}