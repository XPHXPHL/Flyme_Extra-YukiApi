package com.xlp.flyme.extra.activity.pages

import android.view.View
import android.widget.Toast
import cn.fkj233.ui.activity.MIUIActivity.Companion.safeSP
import cn.fkj233.ui.activity.annotation.BMMainPage
import cn.fkj233.ui.activity.data.BasePage
import cn.fkj233.ui.activity.view.SwitchV
import cn.fkj233.ui.activity.view.TextSummaryV
import cn.fkj233.ui.dialog.MIUIDialog
import com.xlp.flyme.extra.R
import com.xlp.flyme.extra.utils.XUtils.execShell
import com.xlp.flyme.extra.utils.XUtils.perfVibratorHelper

@BMMainPage("Flyme Extra")
class MainPage : BasePage() {
    private fun viewToInput(perfKey: String, effectKey: String, textSummaryWithSwitchTextId: Int, textSummaryVTextId: Int, setTitle: Int) {
        val animRatioBinding =
            GetDataBinding({ safeSP.getBoolean(perfKey, false) }) { view, flags, data ->
                if (flags == 1) view.visibility =
                    if (data as Boolean) View.VISIBLE else View.GONE
            }
        TextSummaryWithSwitch(
            TextSummaryV(textId = textSummaryWithSwitchTextId),
            SwitchV(perfKey, onClickListener = { animRatioBinding.send(it) })
        )
        TextSummaryWithArrow(TextSummaryV(textId = textSummaryVTextId, onClickListener = {
            MIUIDialog(activity) {
                setTitle(setTitle)
                setMessage(
                    "${activity.getString(R.string.def)}31021，${activity.getString(R.string.current)}${
                        safeSP.getInt(effectKey, 31021)
                    }"
                )
                setEditText("", "")
                setLButton(textId = R.string.cancel) {
                    dismiss()
                }
                setRButton(textId = R.string.done) {
                    if (getEditText().isNotEmpty()) {
                        runCatching {
                            safeSP.putAny(
                                effectKey,
                                getEditText().toInt()
                            )
                        }.onFailure {
                            Toast.makeText(activity, R.string.warn, Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    dismiss()
                }
                dismiss()
            }.show()
        }), dataBindingRecv = animRatioBinding.getRecv(1))
    }
    override fun onCreate() {
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.blur_enabled), SwitchV("blur_enabled"))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.clock_seconds), SwitchV("clock_seconds", onClickListener = { if (it) { execShell("settings put secure clock_seconds 1") } else { execShell("settings put secure clock_seconds 0") } }))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.forced_screen_capture), SwitchV("forced_screen_capture"))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.unlock_game_fps), SwitchV("unlock_game_fps"))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.app_shade), SwitchV("app_shade"))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.clipboard_editor), SwitchV("clipboard_editor"))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.press_unlock), SwitchV("press_unlock"))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.cipher_disk_vibrator), SwitchV("cipher_disk_vibrator"))
        TextSummaryWithSwitch(TextSummaryV(textId = R.string.hide_imei_sn), SwitchV("hide_imei_sn"))
        viewToInput(perfKey = "vibrator_face", effectKey = "vibrator_effect_id_face", textSummaryWithSwitchTextId = R.string.vibrator_face, textSummaryVTextId = R.string.vibrator_effect_face, setTitle = R.string.vibrator_effect_face)
        viewToInput(perfKey = "vibrator_fingerprint", effectKey = "vibrator_effect_id_fingerprint", textSummaryWithSwitchTextId = R.string.vibrator_fingerprint, textSummaryVTextId = R.string.vibrator_effect_fingerprint, setTitle = R.string.vibrator_effect_fingerprint)
        viewToInput(perfKey = "vibrator_back", effectKey = "vibrator_effect_id_back", textSummaryWithSwitchTextId = R.string.vibrator_back, textSummaryVTextId = R.string.vibrator_effect_back, setTitle = R.string.vibrator_effect_back)

        TextSummaryWithArrow(
            TextSummaryV(textId = R.string.vibrator_effect, onClickListener = {
                MIUIDialog(activity) {
                    setTitle(R.string.vibrator_effect)
                    setLButton(textId = R.string.cancel) {
                        dismiss()
                    }
                    setEditText("", "")
                    setRButton(textId = R.string.done) {
                        if (getEditText().isNotEmpty()) {
                            runCatching {
                                safeSP.putAny(
                                    "vibrator_effect_id",
                                        getEditText().toInt()
                                    )
                                }.onFailure {
                                    Toast.makeText(activity, R.string.warn, Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                            val effectId = safeSP.getInt("vibrator_effect_id", 0)
                            perfVibratorHelper(effectId)
                        }
                        dismiss()
                    }.show()
                }
            )
        )
    }
}