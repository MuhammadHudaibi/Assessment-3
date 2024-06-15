package com.d3if3010.miniproject3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.d3if3010.miniproject3.ui.screen.MainScreen
import com.d3if3010.miniproject3.ui.screen.MakananViewModel
import com.d3if3010.miniproject3.ui.theme.MiniProject3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MiniProject3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel = MakananViewModel()
                    MainScreen(viewModel)
                }
            }
        }
    }
}