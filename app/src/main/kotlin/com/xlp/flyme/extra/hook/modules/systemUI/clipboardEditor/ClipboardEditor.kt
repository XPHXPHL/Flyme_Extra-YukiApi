package com.xlp.flyme.extra.hook.modules.systemUI.clipboardEditor

import com.highcapable.yukihookapi.hook.entity.YukiBaseHooker
import com.highcapable.yukihookapi.hook.factory.method
import com.highcapable.yukihookapi.hook.log.YLog
import com.highcapable.yukihookapi.hook.log.YLog.Configs.tag
import com.xlp.flyme.extra.utils.getBoolean
import de.robv.android.xposed.XposedHelpers

object ClipboardEditor : YukiBaseHooker() {
    override fun onHook() {
        if (getBoolean("clipboard_editor", false)) {
            "com.android.systemui.clipboardoverlay.ClipboardListener".toClass()
                .method {
                    name = "start"
                }.hook {
                    replaceAny {
                        val mClipboardManager =
                            XposedHelpers.getObjectField(this.instance, "mClipboardManager")
                        XposedHelpers.callMethod(
                            mClipboardManager,
                            "addPrimaryClipChangedListener",
                            this.instance
                        )
                    }
                }.onAllFailure {
                    YLog.error(tag = tag, e = it)
                }
        }
    }

}