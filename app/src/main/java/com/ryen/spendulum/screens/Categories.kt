@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.BrightnessSlider
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.ryen.spendulum.components.TableRow
import com.ryen.spendulum.ui.theme.BackGroundElevate
import com.ryen.spendulum.ui.theme.Divider
import com.ryen.spendulum.ui.theme.Primary
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.viewModels.CategoriesViewModel

@Composable
fun Categories(
    modifier: Modifier = Modifier,
    navController: NavController,
    categoriesViewModel: CategoriesViewModel = CategoriesViewModel()
) {
    val state by categoriesViewModel.uiState.collectAsState()
    val controller = rememberColorPickerController()

    Scaffold (
        topBar = {
            MediumTopAppBar(
                title = { Text(text = "Categories", style = Typography.titleLarge) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = TopAppBarBackground,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    Row (verticalAlignment = Alignment.CenterVertically, modifier = modifier.clickable { navController.popBackStack() }){
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                            contentDescription = "Back button",
                            tint = Primary
                        )
                        Text(text = "Settings", style = Typography.labelLarge, color = Primary)
                    }
                }
            )
        },
        content = {innerPadding ->
            Column(
                modifier = modifier.padding(innerPadding)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                        .clip(Shapes.large)
                        .background(BackGroundElevate)
                ) {
                    TableRow(
                        label = "Groceries"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Entertainment"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Electricity"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Bills"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.Start
            ){
                Row {
                    if (state.colorPickerShowing) {
                        Dialog(
                            onDismissRequest = categoriesViewModel::hideColorPicker,
                            properties = DialogProperties(
                                dismissOnBackPress = true,
                                dismissOnClickOutside = true
                            ),
                            content = {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    modifier = Modifier
                                        .clip(RoundedCornerShape(12.dp))
                                        .background(BackGroundElevate)
                                ) {
                                    HsvColorPicker(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(350.dp)
                                            .padding(20.dp),
                                        controller = controller,
                                        initialColor = state.categoryColor,
                                        onColorChanged = { colorEnvelope ->
                                            categoriesViewModel.setCategoryColor(colorEnvelope.color)
                                        }
                                    )
                                    Row(
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ){
                                        BrightnessSlider(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(10.dp)
                                                .height(35.dp),
                                            controller = controller,
                                        )
                                        AlphaTile(
                                            modifier = Modifier
                                                .padding(10.dp)
                                                .clip(CircleShape)
                                                .border(
                                                    width = 3.dp,
                                                    color = Color.White,
                                                    shape = CircleShape
                                                )
                                                .size(45.dp),
                                            controller = controller
                                        )
                                    }
                                    Row (
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp, end = 8.dp)
                                    ){
                                        TextButton (
                                            onClick = {categoriesViewModel.hideColorPicker()}, shape = ButtonDefaults.filledTonalShape){
                                            Text(
                                                text = "Done",
                                                color = Primary,
                                                style = Typography.labelLarge
                                            )
                                        }
                                    }
                                }
                            }
                        )
                    }

                    Surface(
                        onClick = categoriesViewModel::showColorPicker,
                        shape = CircleShape,
                        color = state.categoryColor,
                        modifier = Modifier.size(24.dp)
                    ) { }
                }
            }
        }
    )
}

@Preview
@Composable
private fun CategoriesPreview() {
    SpendulumTheme {
        Categories(navController = rememberNavController())
    }
}