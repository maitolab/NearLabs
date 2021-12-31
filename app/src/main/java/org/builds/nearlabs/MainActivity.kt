package org.builds.nearlabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets
import org.builds.nearlabs.presentation.ui.navigation.AppGraph
import org.builds.nearlabs.presentation.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            AppUI()
        }
    }
}

@Composable
private fun AppUI() {
    ProvideWindowInsets{
        AppTheme{
            Scaffold(
                modifier = Modifier.fillMaxSize()
            ) {
                AppGraph()
            }
        }
    }
}