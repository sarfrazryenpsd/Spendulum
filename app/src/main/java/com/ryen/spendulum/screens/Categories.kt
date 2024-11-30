@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.rounded.Send
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
                modifier = modifier.padding(innerPadding).verticalScroll(rememberScrollState())
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
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Utils"

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
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Bottom,
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(TopAppBarBackground)
                        .height(44.dp)
                        .padding(vertical = 6.dp, horizontal = 16.dp)
                ) {
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
                                    ) {
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
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier.fillMaxWidth()
                                            .padding(bottom = 8.dp, end = 8.dp)
                                    ) {
                                        TextButton(
                                            onClick = { categoriesViewModel.hideColorPicker() },
                                            shape = ButtonDefaults.filledTonalShape
                                        ) {
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
                    // Color Picker Button (Surface)
                    Surface(
                        onClick = categoriesViewModel::showColorPicker,
                        shape = CircleShape,
                        color = state.categoryColor,
                        border = BorderStroke(3.dp, Color.White),
                        modifier = Modifier.size(24.dp) // Explicit size
                    ) { }

                    // TextField
                    TextField(
                        value = state.categoryName,
                        onValueChange = categoriesViewModel::setCategoryName,
                        placeholder = {
                            Text(
                                text = "New Category",
                                color = Color.White,
                            )
                        },
                        shape = Shapes.medium,
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = BackGroundElevate,
                            unfocusedContainerColor = BackGroundElevate,
                            cursorColor = Primary,
                        ),
                        maxLines = 1,
                        trailingIcon = {
                            if (state.categoryName.isNotEmpty()) {
                                IconButton(onClick = { /* Clear action */ }) {
                                    Icon(
                                        imageVector = Icons.Rounded.Close,
                                        contentDescription = null,
                                        modifier = Modifier.size(17.dp)
                                    )
                                }
                            }
                        },
                        modifier = Modifier.weight(1f), // Make TextField fill the remaining space
                    )

                    // Icon Button (Send)
                    Box(
                        modifier = Modifier
                            .size(48.dp) // Fixed size for the Box
                            .background(color = Primary, shape = Shapes.large)
                            .clickable { categoriesViewModel.createCategory() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.Send,
                            contentDescription = "Send",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp) // Icon size
                        )
                    }
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