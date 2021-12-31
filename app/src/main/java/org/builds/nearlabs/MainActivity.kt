package org.builds.nearlabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsHeight
import com.google.accompanist.insets.navigationBarsPadding
import org.builds.nearlabs.presentation.ui.component.BottomTabItem
import org.builds.nearlabs.presentation.ui.navigation.AppGraph
import org.builds.nearlabs.presentation.ui.theme.AppTheme
import org.builds.nearlabs.presentation.ui.theme.Blue

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
    ProvideWindowInsets {
        AppTheme {
            val tabs = remember { BottomTabItem.values() }
            val navController = rememberNavController()

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    AppBottomBar(navController, tabs)
                }
            ) {
                AppGraph()
            }
        }
    }


}

@Composable
private fun AppBottomBar(navController: NavController, tabs: Array<BottomTabItem>) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val routes = remember { tabs.map { it.route } }
    if (currentRoute in routes) {
        BottomNavigation(
            Modifier.navigationBarsHeight(additional = 56.dp),
            backgroundColor = Color.White
        ) {
            tabs.forEach { tab ->
                BottomNavigationItem(
                    icon = { Icon(painterResource(tab.icon), contentDescription = null) },
                    selected = currentRoute == tab.route,
                    onClick = {
                        if (tab.route != currentRoute) {
                            navController.navigate(tab.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = Blue,
                    unselectedContentColor = Color.Black,
                    modifier = Modifier.navigationBarsPadding()
                )
            }
        }
    }
}

