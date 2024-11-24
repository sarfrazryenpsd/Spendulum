@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ryen.spendulum.components.TableRow
import com.ryen.spendulum.ui.theme.BackGroundElevate
import com.ryen.spendulum.ui.theme.Divider
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography

@Composable
fun Add() {
    Scaffold (
        topBar = {
            MediumTopAppBar(title = { Text("Add",style = Typography.titleLarge) }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground,
                titleContentColor = Color.White
            ))
        },
        content = {innerPadding ->
            val textState = remember { mutableStateOf("") }
            val maxAmount = 1000000.00
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
                        label = "Amount",
                        content = {
                            BasicTextField(
                                value = textState.value,
                                onValueChange = { newValue ->
                                    val filteredValue = newValue.filter { it.isDigit() || it == '.' }

                                    // Parse and enforce max value
                                    val amount = filteredValue.toDoubleOrNull() ?: 0.0
                                    if (amount <= maxAmount) {
                                        textState.value = if (filteredValue.count { it == '.' } <= 1) {
                                            // Limit to two decimal places
                                            filteredValue.takeWhile { it.isDigit() || it == '.' }
                                        } else textState.value
                                    }
                                },
                                decorationBox = { innerTextField ->
                                    if (textState.value.isEmpty()) {
                                        // Placeholder text
                                        Text(
                                            text = "$0.00",
                                            color = Color.White.copy(alpha = 0.3f),
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    innerTextField() // The actual text field
                                },
                                textStyle = LocalTextStyle.current.copy(
                                    color = Color.White,
                                    textAlign = TextAlign.End
                                ),
                                singleLine = true,
                                modifier = Modifier.weight(1f),
                                cursorBrush = SolidColor(Color.White),
                                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                            )
                        }
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Recurrence",
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Date",
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Note",
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Category",
                    )
                }
            }
        }
    )
}

@Preview
@Composable
private fun AddPrev() {
    SpendulumTheme {
        Add()
    }
}