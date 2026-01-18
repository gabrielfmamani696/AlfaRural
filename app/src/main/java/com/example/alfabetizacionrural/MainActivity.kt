package com.example.alfabetizacionrural

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.alfabetizacionrural.presentation.MainViewModel
import com.example.alfabetizacionrural.presentation.navigation.AppDestinations
import com.example.alfabetizacionrural.presentation.navigation.AppNavigationGraph

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    val isLoading by mainViewModel.isLoading.collectAsState()
                    val currentUser by mainViewModel.currentUser.collectAsState()

                    if (isLoading) {
                        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                            CircularProgressIndicator()
                        }
                    } else {
                        val startDestination = if (currentUser != null) {
                            AppDestinations.MAIN_DASHBOARD
                        } else {
                            AppDestinations.REGISTRATION
                        }

                        AppNavigationGraph(
                            navController = navController,
                            startDestination = startDestination,
                            onFinish = { finish() }
                        )
                    }
                }
            }
        }
    }
}