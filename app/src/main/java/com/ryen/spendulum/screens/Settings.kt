@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.ryen.spendulum.components.AlertDialog2
import com.ryen.spendulum.components.TableRow
import com.ryen.spendulum.data.AppDatabase
import com.ryen.spendulum.data.repository.CategoryRepository
import com.ryen.spendulum.ui.theme.BackGroundElevate
import com.ryen.spendulum.ui.theme.Divider
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.viewModels.SettingsViewModel
import com.ryen.spendulum.viewModels.viewModelFactory

@Composable
fun Settings(navController: NavController) {
    val context = LocalContext.current
    val settingsViewModel: SettingsViewModel = viewModel(
        factory = viewModelFactory {
            SettingsViewModel(CategoryRepository(AppDatabase.getInstance(context).categoryDao()))
        }
    )

    var showDialog by remember { mutableStateOf(false) }
    Scaffold (
        topBar = {
            MediumTopAppBar(title = { Text(text = "Settings", style = Typography.titleLarge) }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground,
                titleContentColor = Color.White
            ))
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth()
                        .clip(Shapes.large)
                        .background(BackGroundElevate)
                ) {
                    TableRow(
                        label = "Categories",
                        hasArrow = true,
                        modifier = Modifier.clickable { navController.navigate("settings/categories") },
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Erase Data",
                        isDestructive = true,
                        modifier = Modifier.clickable { showDialog = !showDialog },
                        content = {
                            if (showDialog){
                                AlertDialog2(
                                    onDismissRequest = { showDialog = false },
                                    onConfirmation = {
                                        settingsViewModel.deleteALlCategories()
                                        showDialog = false
                                    },
                                    dialogTitle = "Confirm Data Deletion",
                                    dialogText = "Are you sure you want to erase all categories and expenses? This action cannot be undone, and all the data will be permanently deleted.",
                                    icon = Icons.Default.Warning
                                )
                            }
                        }
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun SettingsPrev() {
    SpendulumTheme {
        Settings(navController = rememberNavController())
    }
}