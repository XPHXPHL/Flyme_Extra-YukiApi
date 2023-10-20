package com.xlp.flyme.extra.activity.pages

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.activity.annotation.BMMenuPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextV
import cn.fkj233.ui.dialog.MIUIDialog
import com.xlp.flyme.extra.BuildConfig
import com.xlp.flyme.extra.R
import com.xlp.flyme.extra.activity.MainActivity
import com.xlp.flyme.extra.utils.BackupUtils
import com.xlp.flyme.extra.utils.XUtils.execShell
import java.text.SimpleDateFormat
import java.util.Locale


@BMMenuPage
class MenuPage : BasePage() {
    override fun getTitle(): String {
        setTitle(getString(R.string.menu))
        return getString(R.string.menu)
    }

    @SuppressLint("WorldReadableFiles", "SdCardPath")
    @Suppress("DEPRECATION")
    override fun onCreate() {
        TextWithSwitch(
            TextV(textId = R.string.hide_desktop_icon),
            SwitchV("hide_desktop_icon", onClickListener = {
                val pm = MIUIActivity.activity.packageManager
                val mComponentEnabledState: Int = if (it) {
                    PackageManager.COMPONENT_ENABLED_STATE_DISABLED
                } else {
                    PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                }
                pm.setComponentEnabledSetting(
                    ComponentName(
                        MIUIActivity.activity,
                        MainActivity::class.java.name + "Alias"
                    ), mComponentEnabledState, PackageManager.DONT_KILL_APP
                )
            })
        )
        TextWithArrow(TextV(textId = R.string.backup, onClickListener = {
            BackupUtils.backup(
                activity,
                activity.createDeviceProtectedStorageContext()
                    .getSharedPreferences("Flyme-Extra_Config", Context.MODE_WORLD_READABLE)
            )
        }))
        TextWithArrow(TextV(textId = R.string.recovery, onClickListener = {
            BackupUtils.recovery(
                activity,
                activity.createDeviceProtectedStorageContext()
                    .getSharedPreferences("Flyme-Extra_Config", Context.MODE_WORLD_READABLE)
            )
        }))
        TextWithArrow(TextV(textId = R.string.restart_scope) {
            MIUIDialog(activity) {
                setTitle(R.string.tips)
                setMessage(R.string.restart_scope_summary)
                setLButton(R.string.cancel) {
                    dismiss()
                }
                setRButton(R.string.done) {
                    val command = arrayOf(
                        "killall com.android.systemui",
                        "killall com.flyme.systemuiex",
                        "killall com.android.settings",
                        "killall com.android.packageinstaller",
                    )
                    execShell(command)
                    Toast.makeText(
                        activity,
                        getString(R.string.restart_scope_finished),
                        Toast.LENGTH_SHORT
                    ).show()
                    Thread.sleep(500)
                    dismiss()
                }
            }.show()
        })
        TextWithArrow(TextV(textId = R.string.reboot_system) {
            MIUIDialog(activity) {
                setTitle(R.string.tips)
                setMessage(R.string.reboot_system_summary)
                setLButton(R.string.cancel) {
                    dismiss()
                }
                setRButton(R.string.done) {
                    execShell("reboot")
                }
            }.show()
        })
        Line()
        TextSummary(
            textId = R.string.module_build_time,
            tips = "${BuildConfig.VERSION_NAME}-${BuildConfig.BUILD_TYPE}"
        )
        val buildTime =
            SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(BuildConfig.BUILD_TIME)
        TextSummary(textId = R.string.module_build_time, tips = buildTime)
    }
}