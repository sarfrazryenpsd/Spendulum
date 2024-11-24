package com.ryen.spendulum

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.ryen.spendulum.screens.Add
import com.ryen.spendulum.screens.Expenses
import com.ryen.spendulum.screens.Settings
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpendulumTheme {
                val navController = rememberNavController()
                val backStackEntry = navController.currentBackStackEntryAsState()
                Scaffold(
                    bottomBar = {
                        NavigationBar(
                            containerColor = TopAppBarBackground
                        ) {
                            NavigationBarItem(
                                selected = backStackEntry.value?.destination?.route == "expenses",
                                onClick = { navController.navigate("expenses") },
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
                                onClick = { navController.navigate("reports") },
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
                                onClick = { navController.navigate("add") },
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
                                selected = backStackEntry.value?.destination?.route?.startsWith("settings") ?: false,
                                onClick = { navController.navigate("settings") },
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
                    },
                    content = { paddingValues ->
                        NavHost(
                            navController = navController,
                            startDestination = "reports",
                            modifier = Modifier.padding(paddingValues)
                        ){
                            composable("expenses") {
                                Expenses(navController)
                            }
                            composable("reports") {
                                Greeting(
                                    name = "Reports",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                            composable("add") {
                                Add()
                            }
                            composable("settings") {
                                Settings(navController)
                            }
                            composable("settings/categories") {
                                Greeting(
                                    name = "Categories",
                                    modifier = Modifier.fillMaxSize()
                                )
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SpendulumTheme {
        Greeting("Android")
    }
}