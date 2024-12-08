@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
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
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.AlphaTile
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import com.ryen.spendulum.R
import com.ryen.spendulum.components.TableRow
import com.ryen.spendulum.ui.theme.BackGroundElevate
import com.ryen.spendulum.ui.theme.Destructive
import com.ryen.spendulum.ui.theme.Divider
import com.ryen.spendulum.ui.theme.Primary
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.viewModels.CategoriesViewModel
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

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
                modifier = modifier
                    .padding(innerPadding)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .padding(start = 12.dp, end = 12.dp, top = 12.dp)
                        .fillMaxWidth()
                        .clip(Shapes.large)
                        //.background(BackGroundElevate)
                ) {
                    itemsIndexed(
                        state.categories

                    ){ index,category ->
                        SwipeableActionsBox(
                            endActions = listOf(
                                SwipeAction(
                                    icon = painterResource(R.drawable.baseline_delete_white_36),
                                    background = Destructive,
                                    onSwipe = { categoriesViewModel.deleteCategory(category) },
                                    isUndo = true
                                ),
                            ),
                            modifier = Modifier.animateItem()
                        ){
                            TableRow(
                                modifier = Modifier.background(BackGroundElevate),
                                detailContent = {
                                    Surface(
                                        onClick = categoriesViewModel::showColorPicker,
                                        shape = CircleShape,
                                        color = category.color,
                                        border = BorderStroke(2.dp, Color.White),
                                        modifier = Modifier.size(16.dp) // Explicit size
                                    ) { }
                                },
                                label = category.name
                            )
                            if (index < state.categories.size || index == 0) {
                                Row(modifier = Modifier.background(BackGroundElevate).height(1.dp)){
                                    HorizontalDivider(
                                        color = Divider,
                                        thickness = 1.2.dp,
                                        modifier = Modifier.padding(horizontal = 8.dp)
                                    )
                                }
                            }
                        }
                    }
                    item {
                        if(state.categories.size > 15){
                            TableRow(
                                label = "",
                                modifier = Modifier.height(70.dp)
                            )
                        }
                    }

                }
            }
            Column (
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(bottom = 12.dp),
                verticalArrangement = Arrangement.Bottom,
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .padding(vertical = 6.dp, horizontal = 10.dp)
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
                                        /*BrightnessSlider(
                                            modifier = Modifier
                                                .weight(1f)
                                                .padding(10.dp)
                                                .height(35.dp),
                                            controller = controller,
                                        )*/
                                        AlphaTile(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .height(45.dp),
                                            controller = controller
                                        )
                                    }
                                    Row(
                                        horizontalArrangement = Arrangement.End,
                                        modifier = Modifier
                                            .fillMaxWidth()
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .height(44.dp)
                            .clip(Shapes.medium)
                            .background(Color.Black)
                            .border(1.2.dp, Color.White.copy(alpha = 0.5f), Shapes.medium),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        BasicTextField(
                            value = state.categoryName,
                            onValueChange = { newValue ->
                                 categoriesViewModel.setCategoryName(newValue)
                            },

                            decorationBox = { innerTextField ->
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                ) {
                                    if (state.categoryName.isEmpty()) {
                                        // Placeholder text
                                        Text(
                                            text = "Add category",
                                            color = Color.White.copy(alpha = 0.2f),
                                            //textAlign = TextAlign.Left,
                                            style = Typography.bodyMedium
                                        )
                                    }
                                }
                                innerTextField() // The actual text field
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(start = 16.dp),
                            textStyle = LocalTextStyle.current.copy(
                                color = Color.White,
                                textAlign = TextAlign.Start
                            ),
                            singleLine = true,
                            cursorBrush = SolidColor(Color.White),
                        )
                        AnimatedVisibility(visible = state.categoryName.isNotEmpty()) {
                            Box(
                                modifier = Modifier.size(44.dp),
                                contentAlignment = Alignment.CenterEnd,
                            ) {
                                IconButton(onClick = {categoriesViewModel.setCategoryName("")}) {
                                    Icon(
                                        painter = painterResource(R.drawable.cancel),
                                        contentDescription = null,
                                        modifier = Modifier.size(17.dp)
                                    )
                                }
                            }
                        }
                    }

                    // Icon Button (Add)
                    AnimatedVisibility(visible = state.categoryName.isNotEmpty()){
                        Box(
                            modifier = Modifier
                                .size(48.dp) // Fixed size for the Box
                                .background(color = Primary, shape = Shapes.large)
                                .clickable {
                                    if (state.categoryName.isNotEmpty()) {
                                        categoriesViewModel.createCategory()
                                    }
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Add,
                                contentDescription = "Send",
                                tint = Color.White,
                                modifier = Modifier.size(28.dp) // Icon size
                            )
                        }
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