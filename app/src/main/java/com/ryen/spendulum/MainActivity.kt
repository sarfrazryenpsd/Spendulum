package com.ryen.spendulum

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ryen.spendulum.screens.Add
import com.ryen.spendulum.screens.Categories
import com.ryen.spendulum.screens.Expenses
import com.ryen.spendulum.screens.Reports
import com.ryen.spendulum.screens.Settings
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import io.sentry.compose.withSentryObservableEffect

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        @Suppress("DEPRECATION")
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        setContent {
            SpendulumTheme {
                var showBottomBar by rememberSaveable { mutableStateOf(true) }
                val navController = rememberNavController().withSentryObservableEffect()
                val backStackEntry = navController.currentBackStackEntryAsState()

                showBottomBar = backStackEntry.value?.destination?.route != "settings/categories"

                Scaffold(
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar(
                                containerColor = TopAppBarBackground
                            ) {
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == "expenses",
                                    onClick = { navController.navigate("expenses"){
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // re-selecting the same item
                                        launchSingleTop = true
                                        // Restore state when re-selecting a previously selected item
                                        restoreState = true
                                    } },
                                    label = {
                                        Text(text = "Expenses")
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(R.drawable.payments),
                                            contentDescription = null
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == "reports",
                                    onClick = { navController.navigate("reports"){
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // re-selecting the same item
                                        launchSingleTop = true
                                        // Restore state when re-selecting a previously selected item
                                        restoreState = true
                                    } },
                                    label = {
                                        Text(text = "Reports")
                                    },
                                    icon = {
                                        Icon(
                                            painter = painterResource(R.drawable.bar_chart),
                                            contentDescription = null
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route == "add",
                                    onClick = { navController.navigate("add"){
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // re-selecting the same item
                                        launchSingleTop = true
                                        // Restore state when re-selecting a previously selected item
                                        restoreState = true
                                    } },
                                    label = {
                                        Text(text = "Add")
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Add,
                                            contentDescription = null
                                        )
                                    }
                                )
                                NavigationBarItem(
                                    selected = backStackEntry.value?.destination?.route?.startsWith(
                                        "settings"
                                    ) ?: false,
                                    onClick = { navController.navigate("settings"){
                                        // Pop up to the start destination of the graph to
                                        // avoid building up a large stack of destinations
                                        // on the back stack as users select items
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        // Avoid multiple copies of the same destination when
                                        // re-selecting the same item
                                        launchSingleTop = true
                                        // Restore state when re-selecting a previously selected item
                                        restoreState = true
                                    } },
                                    label = {
                                        Text(text = "Settings")
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = Icons.Default.Settings,
                                            contentDescription = null
                                        )
                                    }
                                )
                            }
                        }
                    },
                    content = { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = "expenses",
                            modifier = Modifier.padding(paddingValues)
                        ){
                            composable("expenses") {
                                Expenses()
                            }
                            composable("reports") {
                                Reports()
                            }
                            composable("add") {
                                Add()
                            }
                            composable("settings") {
                                Settings(navController)
                            }
                            composable("settings/categories") {
                                Categories(navController = navController)
                            }
                        }
                    }
                )
            }
        }
    }
}

