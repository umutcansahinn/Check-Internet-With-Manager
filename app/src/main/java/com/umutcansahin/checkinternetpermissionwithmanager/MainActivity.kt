package com.umutcansahin.checkinternetpermissionwithmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.umutcansahin.checkinternetpermissionwithmanager.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dialog: AlertDialog
    @Inject
    lateinit var networkManager: NetworkManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dialog = MaterialAlertDialogBuilder(this, R.style.MaterialAlertDialog_Rounded)
            .setView(R.layout.custom_dialog)
            .setCancelable(false)
            .create()

        networkManager.observe(this) { networkStatus ->
            networkStatus?.let { status->
                when (status) {
                    Status.Lost -> if (dialog.isShowing.not()) dialog.show()
                    Status.Unavailable -> if (dialog.isShowing.not()) dialog.show()
                    Status.Available -> if (dialog.isShowing) dialog.hide()
                }
            }
        }
    }
}