package com.xlp.flyme.extra.hook.modules.systemUI.vibrator.back

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.XUtils

object BackVibrator : YukiBaseHooker() {
    override fun onHook() {
        "com.flyme.systemui.navigationbar.gestural.EdgeBackView".toClass()
            .method {
                name = "triggerBack"
            }.hook {
                after {
                    XUtils.vibratorHelper(31021)
                }
            }.result {
                onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
            }
    }
}