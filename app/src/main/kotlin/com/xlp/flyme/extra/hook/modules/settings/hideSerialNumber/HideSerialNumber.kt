package com.xlp.flyme.extra.hook.modules.settings.hideSerialNumber

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean

object HideSerialNumber : YukiBaseHooker() {

    override fun onHook() {
        if (getBoolean("hide_imei_sn", false)) {
            "com.meizu.settings.utils.MzUtils".toClass()
                .method {
                    name = "getSerialNumber"
                }.hook {
                    after {
                        val serialNumber = this.result.toString().split(',')
                        val hideSerialNumber =
                            serialNumber.joinToString { it.take(3) + "*".repeat(it.length - 3) }
                        this.result = hideSerialNumber
                    }
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}