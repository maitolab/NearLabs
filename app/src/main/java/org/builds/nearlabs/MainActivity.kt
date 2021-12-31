package org.builds.nearlabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.builds.nearlabs.presentation.ui.navigation.AppGraph
import org.builds.nearlabs.presentation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            AppTheme{
                AppUI()
            }
        }
    }
}

@Composable
private fun AppUI(){
    Scaffold(
        modifier = Modifier.fillMaxSize()) {
        AppGraph()
    }
}