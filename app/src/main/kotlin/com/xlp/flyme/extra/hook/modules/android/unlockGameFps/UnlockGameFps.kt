package com.xlp.flyme.extra.hook.modules.android.unlockGameFps

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean

object UnlockGameFps : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("unlock_game_fps", false)) {
            "com.android.server.wm.WindowState".toClass()
                .method {
                    name = "isFocused"
                }.hook {
                    replaceToFalse()
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}
