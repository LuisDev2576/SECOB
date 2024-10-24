
package com.luisdev2576.secob

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.luisdev2576.secob.navigation.Navigation
import com.luisdev2576.secob.ui.theme.SECOBTheme
import com.luisdev2576.secob.utils.BiometricPromptManager
import com.luisdev2576.secob.utils.MyNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val promptManager by lazy { BiometricPromptManager(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navHostController = rememberNavController()
            SECOBTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { paddingValues ->
                        Surface(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        ) {
                            Navigation(promptManager = promptManager, navHostController = navHostController)
                        }
                    },
                    bottomBar = {
                        MyNavigationBar(navHostController = navHostController)
                    }
                )
            }
        }
    }
}

