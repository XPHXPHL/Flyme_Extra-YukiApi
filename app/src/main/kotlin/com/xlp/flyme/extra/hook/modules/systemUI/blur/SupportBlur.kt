package com.xlp.flyme.extra.hook.modules.systemUI.blur

import android.util.Log
import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag

object SupportBlur : YukiBaseHooker() {
    override fun onHook() {
        fun isSupportBlur(clazz: Class<*>) {
            clazz
                .method {
                    name = "isSupportBlur"
                }.hook {
                    replaceToTrue()
                }.result {
                    onAllFailure {
                        Log.e(tag, "${it.message}")
                    }
                }
        }

        val targetClass =
            "com.android.systemui.statusbar.notification.row.ActivatableNotificationView".toClass()
        val targetClass2 =
            "com.android.systemui.statusbar.notification.stack.NotificationStackScrollLayout".toClass()
        val targetClass3 = "com.android.systemui.media.MediaCarouseTransitionLayout".toClass()
        isSupportBlur(targetClass)
        isSupportBlur(targetClass2)
        isSupportBlur(targetClass3)
    }
}