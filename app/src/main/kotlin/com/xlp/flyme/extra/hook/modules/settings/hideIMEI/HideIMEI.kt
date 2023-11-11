package com.xlp.flyme.extra.hook.modules.settings.hideIMEI

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean

object HideIMEI : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("hide_imei_sn", false)) {
            "com.meizu.settings.utils.MzUtils".toClass()
                .method {
                    name = "getImei"
                }.hook {
                    after {
                        val imei = this.result.toString().split(',')
                        val hideIMEI =
                            imei.joinToString(",") { it.take(5) + "*".repeat(it.length - 3) }
                        this.result = hideIMEI
                    }
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}