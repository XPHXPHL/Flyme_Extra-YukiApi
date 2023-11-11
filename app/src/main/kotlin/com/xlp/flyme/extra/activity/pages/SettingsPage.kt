package com.xlp.flyme.extra.activity.pages

import cn.fkj233.ui.activity.annotation.BMPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import com.xlp.flyme.extra.R

@BMPage("SettingsPage", hideMenu = false)
class SettingsPage : BasePage() {
    override fun getTitle(): String {
        setTitle(getString(R.string.settings_page))
        return getString(R.string.settings_page)
    }

    override fun onCreate() {
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.cipher_disk_vibrator, tipsId = R.string.cipher_disk_vibrator_summary),
            SwitchV("cipher_disk_vibrator")
        )
        TextSummaryWithSwitch(
            TextSummaryV(textId = R.string.press_unlock, tipsId = R.string.press_unlock_summary),
            SwitchV("press_unlock")
        )
    }
}