package com.xlp.flyme.extra.hook.modules.systemuieditor.fuckLimit

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean

object FuckLimit : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("fuck_limit", false)) {
            "r3.n".toClass()
                .method {
                    name = "m"
                    param(Boolean::class.java, Int::class.java)
                }.hook {
                    replaceToTrue()
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}