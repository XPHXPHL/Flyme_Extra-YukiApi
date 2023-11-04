package com.xlp.flyme.extra.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import cn.fkj233.ui.activity.MIUIActivity
import cn.fkj233.ui.dialog.MIUIDialog
import com.xlp.flyme.extra.R
import com.xlp.flyme.extra.activity.pages.MainPage
import com.xlp.flyme.extra.activity.pages.MenuPage
import com.xlp.flyme.extra.activity.pages.SystemUiPage
import com.xlp.flyme.extra.activity.pages.VibratorPage
import com.xlp.flyme.extra.utils.BackupUtils
import kotlin.system.exitProcess


class MainActivity : MIUIActivity() {
    @SuppressLint("SdCardPath")
    override fun onCreate(savedInstanceState: Bundle?) {
        checkLSPosed()
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("WorldReadableFiles")
    @Suppress("DEPRECATION")
    private fun checkLSPosed() {
        try {
            setSP(getSharedPreferences("Flyme-Extra_Config", MODE_WORLD_READABLE))
        } catch (exception: SecurityException) {
            isLoad = false
            MIUIDialog(this) {
                setTitle(R.string.tips)
                setMessage(R.string.not_support)
                setCancelable(false)
                setRButton(R.string.done) {
                    exitProcess(0)
                }
            }.show()
        }
    }


    init {
        activity = this
        registerPage(MenuPage::class.java)
        registerPage(MainPage::class.java)
        registerPage(VibratorPage::class.java)
        registerPage(SystemUiPage::class.java)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null && resultCode == RESULT_OK) {
            when (requestCode) {
                BackupUtils.CREATE_DOCUMENT_CODE -> {
                    BackupUtils.handleCreateDocument(activity, data.data)
                }

                BackupUtils.OPEN_DOCUMENT_CODE -> {
                    BackupUtils.handleReadDocument(activity, data.data)
                }

            }
        }
    }

}
