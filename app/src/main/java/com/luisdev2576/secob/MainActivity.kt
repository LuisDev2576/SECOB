
package com.luisdev2576.secob

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.luisdev2576.secob.navigation.Navigation
import com.luisdev2576.secob.ui.theme.SECOBTheme
import com.luisdev2576.secob.utils.BiometricPromptManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val promptManager by lazy { BiometricPromptManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SECOBTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    Navigation(promptManager = promptManager)
                }
            }
        }
    }
}