package com.xlp.flyme.extra.hook.modules.systemUIex.forcedScreenCapture

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean

object ForcedScreenCapture2 : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("forced_screen_capture", false)) {
            "android.view.SurfaceControl\$ScreenshotHardwareBuffer".toClass()
                .method {
                    name = "containsSecureLayers"
                }
                .hook {
                    replaceToFalse()
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }

    }
}