package com.xlp.flyme.extra.activity.pages

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.xlp.flyme.extra.R
import com.xlp.flyme.extra.utils.XUtils.execShell

@BMPage("SystemUiPage", hideMenu = false)
class SystemUiPage:BasePage() {
    override fun getTitle(): String {
        setTitle(getString(R.string.systemui_page))
        return getString(R.string.systemui_page)
    }

    override fun onCreate() {
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.blur_enabled, tipsId = R.string.blur_enabled_summary),
            SwitchV("blur_enabled")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.clock_seconds, tipsId = R.string.clock_seconds_summary),
            SwitchV("clock_seconds", onClickListener = {
                if (it){
                    execShell("settings put secure clock_seconds 1")
                }else{
                    execShell("settings put secure clock_seconds 0")
                }
            })
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.app_shade, tipsId = R.string.app_shade_summary),
            SwitchV("app_shade")
        )
    }
}