@file:OptIn(ExperimentalMaterial3Api::class)

package com.ryen.spendulum.screens

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
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
            var textState by remember { mutableStateOf("") }
            var noteState by remember { mutableStateOf("") }
            val maxAmount = 1000000.00

//            var openDatePicker = remember { mutableStateOf(true) }

            val context = LocalContext.current

            val mDay: Int
            val mMonth: Int
            val mYear: Int
            val mCalendar = Calendar.getInstance()

            mYear = mCalendar.get(Calendar.YEAR)
            mMonth = mCalendar.get(Calendar.MONTH)
            mDay = mCalendar.get(Calendar.DAY_OF_MONTH)
            var dateResult by remember { mutableStateOf(
                "${mCalendar.get(Calendar.DAY_OF_MONTH)}-${mCalendar.get(Calendar.MONTH) + 1 }-${mCalendar.get(Calendar.YEAR)}"
            ) }

            val mDatePicker = DatePickerDialog(
                context,
                { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
                    dateResult = "${selectedDayOfMonth}-${selectedMonth + 1 }-${selectedYear}"
                },
                mYear,
                mMonth,
                mDay
            )
            mDatePicker.datePicker.maxDate = mCalendar.timeInMillis

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
                                value = textState,
                                onValueChange = { newValue ->
                                    val filteredValue = newValue.filter { it.isDigit() || it == '.' }

                                    // Parse and enforce max value
                                    val amount = filteredValue.toDoubleOrNull() ?: 0.0
                                    if (amount <= maxAmount) {
                                        textState = if (filteredValue.count { it == '.' } <= 1) {
                                            // Limit to two decimal places
                                            filteredValue.takeWhile { it.isDigit() || it == '.' }
                                        } else textState
                                    }
                                },
                                decorationBox = { innerTextField ->
                                    if (textState.isEmpty()) {
                                        // Placeholder text
                                        Text(
                                            text = "$0.00",
                                            color = Color.White.copy(alpha = 0.2f),
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
                        content = {
                            var expanded by remember { mutableStateOf(false) } // Track DropdownMenu state
                            var recurrenceItem by remember { mutableStateOf("None") } // Track selected item // Track selected item

                            Row(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }, // Make Row clickable
                                verticalAlignment = Alignment.CenterVertically // Align Text and Icon
                            ) {
                                // Display the selected text
                                Text(
                                    text = recurrenceItem,
                                    style = Typography.bodyMedium,
                                    color = ButtonDefaults.buttonColors().containerColor
                                )

                                // Down Arrow Icon
                                Icon(
                                    imageVector = if(!expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                                    contentDescription = "Dropdown Arrow",
                                    tint = ButtonDefaults.buttonColors().containerColor
                                )


                                // Dropdown Menu
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = {
                                        expanded = false
                                    } ,// Close menu when clicked outside
                                    modifier = Modifier.background(color = TopAppBarBackground)
                                ) {
                                    val items = listOf("None", "Daily", "Weekly", "Monthly", "Yearly") // Dropdown items", "Option 3") // Dropdown items
                                    items.forEach { item ->
                                        DropdownMenuItem(
                                            text = {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Surface(modifier = Modifier.size(14.dp), shape = CircleShape, color = Color.Blue) {  }
                                                    Text(text = item, modifier = Modifier.padding(start = 8.dp))
                                                }
                                            },
                                            onClick = {
                                                recurrenceItem = item // Update selected item
                                                expanded = false // Close the menu
                                            },
                                            contentPadding = PaddingValues(horizontal = 8.dp)
                                        )
                                    }
                                }
                            }
                        }
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Date",
                        content = {

                            Row(
                                modifier = Modifier
                                    .clickable { mDatePicker.show() }, // Make Row clickable
                                verticalAlignment = Alignment.CenterVertically // Align Text and Icon
                            ){
                                Text(
                                    text = dateResult,
                                    color = ButtonDefaults.buttonColors().containerColor
                                )
                                Icon(
                                    imageVector = Icons.Default.DateRange,
                                    contentDescription = "Date Icon",
                                    tint = ButtonDefaults.buttonColors().containerColor,
                                    modifier = Modifier.padding(start = 4.dp).size(20.dp)
                                )
                            }

                        }
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Notes",
                        content = {
                            BasicTextField(
                                value = noteState,
                                onValueChange = { newValue ->
                                    noteState = newValue
                                },
                                decorationBox = { innerTextField ->
                                    if (textState.isEmpty()) {
                                        // Placeholder text
                                        Text(
                                            text = "Leave some notes...",
                                            color = Color.White.copy(alpha = 0.2f),
                                            textAlign = TextAlign.End
                                        )
                                    }
                                    innerTextField() // The actual text field
                                },
                                textStyle = LocalTextStyle.current.copy(
                                    color = Color.White,
                                    textAlign = TextAlign.End
                                ),
                                maxLines = 3,
                                //singleLine = true,
                                modifier = Modifier.weight(1f),
                                cursorBrush = SolidColor(Color.White),

                            )
                        }
                    )
                    HorizontalDivider(
                        color = Divider,
                        thickness = 1.dp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                    TableRow(
                        label = "Category",
                        content = {

                            var expanded by remember { mutableStateOf(false) } // Track DropdownMenu state
                            var categoryItem by remember { mutableStateOf("Grocery") } // Track selected item // Track selected item

                            Row(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }, // Make Row clickable
                                verticalAlignment = Alignment.CenterVertically // Align Text and Icon
                            ) {
                                // Display the selected text
                                Text(
                                    text = categoryItem,
                                    style = Typography.bodyMedium,
                                    color = ButtonDefaults.buttonColors().containerColor
                                )

                                // Down Arrow Icon
                                Icon(
                                    imageVector = if(!expanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowUp,
                                    contentDescription = "Dropdown Arrow",
                                    tint = ButtonDefaults.buttonColors().containerColor
                                )


                                // Dropdown Menu
                                DropdownMenu(
                                    expanded = expanded,
                                    onDismissRequest = {
                                        expanded = false
                                    } ,// Close menu when clicked outside
                                    modifier = Modifier.background(color = TopAppBarBackground)
                                ) {
                                    val items = listOf("Grocery", "Bills", "Entertainment", "Other", "Hobbies", "Takeout") // Dropdown items", "Option 3") // Dropdown items
                                    items.forEach { item ->
                                        DropdownMenuItem(
                                            text = {
                                                Row(verticalAlignment = Alignment.CenterVertically){
                                                    Surface(modifier = Modifier.size(10.dp), shape = CircleShape, color = Color.Blue) {  }
                                                    Text(text = item, style = Typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
                                                }
                                            },
                                            onClick = {
                                                categoryItem = item // Update selected item
                                                expanded = false // Close the menu
                                            })
                                    }
                                }
                            }
                        }
                    )
                }
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Submit expense",
                        style = Typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Color.White,
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