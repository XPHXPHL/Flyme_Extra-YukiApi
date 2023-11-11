package com.xlp.flyme.extra.hook.modules.systemUI.vibrator.back

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.XUtils
import com.xlp.flyme.extra.utils.getBoolean
import com.xlp.flyme.extra.utils.getInt

object BackVibrator : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("vibrator_back", false)) {
            "com.flyme.systemui.navigationbar.gestural.EdgeBackView".toClass()
                .method {
                    name = "triggerBack"
                }.hook {
                    after {
                        val effectId = getInt("vibrator_effect_id_back", 31021)
                        XUtils.vibratorHelper(effectId)
                    }
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }
}