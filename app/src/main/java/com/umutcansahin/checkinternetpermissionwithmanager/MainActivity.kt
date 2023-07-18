package com.umutcansahin.checkinternetpermissionwithmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.umutcansahin.checkinternetpermissionwithmanager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = MaterialAlertDialogBuilder(this,R.style.MaterialAlertDialog_Rounded)
            .setView(R.layout.custom_dialog)
            .setCancelable(false)
            .create()

        val networkManager = NetworkManager(this)
        networkManager.observe(this) {
            if (it.not()) {
                if(dialog.isShowing.not()) dialog.show()
            }else {
                if (dialog.isShowing) dialog.hide()
            }
        }
    }
}