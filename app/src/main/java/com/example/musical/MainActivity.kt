package com.example.musical

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.example.musical.ui.theme.MusicalTheme

class MainActivity : ComponentActivity() {
    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                // Permission is granted. Continue the action or workflow in your app.
            } else {
                // Permission is denied. Inform the user that the feature is unavailable.
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MusicalTheme {
                RequestPermissions()
                MainView()
            }
        }
    }

    @Composable
    fun RequestPermissions() {
        val context = LocalContext.current
        val permission = Manifest.permission.RECORD_AUDIO

        val launcher = remember {
            requestPermissionLauncher
        }

        SideEffect {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                launcher.launch(permission)
            }
        }
    }
}
