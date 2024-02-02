package com.xlp.flyme.extra.hook.modules.systemUITools.marbleRate

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.XUtils.writeNode
import com.xlp.flyme.extra.utils.getBoolean

object MarbleRate : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("marble_rate", false)) {
            "com.flyme.systemuitools.gameassiant.gamemode.controller.MzGameModeController".toClass()
                .method {
                    name = "Z0"
                    param(Boolean::class.java)
                }.hook {
                    after {
                        when (this.args[0]) {
                            "0" -> writeNode(
                                "/sys/devices/platform/goodix_tx.0/goodix_ts_reprot_rate",
                                "0"
                            )

                            "1" -> writeNode(
                                "/sys/devices/platform/goodix_tx.0/goodix_ts_reprot_rate",
                                "1"
                            )

                            null -> YLog.error(tag = tag, msg = "marble_rate：")
                        }
                    }
                }
        }
    }
}