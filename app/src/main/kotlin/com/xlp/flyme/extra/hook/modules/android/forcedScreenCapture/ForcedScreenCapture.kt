package com.xlp.flyme.extra.hook.modules.android.forcedScreenCapture

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean

object ForcedScreenCapture : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("forced_screen_capture", false)) {
            "com.android.server.wm.WindowState".toClass()
                .method {
                    name = "isSecureLocked"
                }.hook {
                    replaceToFalse()
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}