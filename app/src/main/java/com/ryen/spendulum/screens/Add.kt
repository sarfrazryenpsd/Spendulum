@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)

package com.ryen.spendulum.screens

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.marosseleng.compose.material3.datetimepickers.date.ui.dialog.DatePickerDialog
import com.ryen.spendulum.components.TableRow
import com.ryen.spendulum.data.AppDatabase
import com.ryen.spendulum.data.entity.Category
import com.ryen.spendulum.data.repository.ExpenseRepository
import com.ryen.spendulum.models.Recurrence
import com.ryen.spendulum.ui.theme.BackGroundElevate
import com.ryen.spendulum.ui.theme.Divider
import com.ryen.spendulum.ui.theme.Shapes
import com.ryen.spendulum.ui.theme.SpendulumTheme
import com.ryen.spendulum.ui.theme.TopAppBarBackground
import com.ryen.spendulum.ui.theme.Typography
import com.ryen.spendulum.viewModels.AddViewModel
import com.ryen.spendulum.viewModels.viewModelFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun Add() {
    val context = LocalContext.current
    val addViewModel: AddViewModel = viewModel(
        factory = viewModelFactory {
            AddViewModel(ExpenseRepository(AppDatabase.getInstance(context).expenseDao()))
        }
    )
    val state by addViewModel.state.collectAsState()

    Scaffold (
        topBar = {
            MediumTopAppBar(title = { Text("Add",style = Typography.titleLarge) }, colors = TopAppBarDefaults.mediumTopAppBarColors(
                containerColor = TopAppBarBackground,
                titleContentColor = Color.White
            ))
        },
        content = {innerPadding ->
            val maxAmount = 1000000.00

            val recurrences = listOf(
                Recurrence.None,
                Recurrence.Daily,
                Recurrence.Weekly,
                Recurrence.Monthly,
                Recurrence.Yearly
            )



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
                                value = state.amount,
                                onValueChange = { newValue: String ->
                                    val filteredValue = newValue.filter { it.isDigit() || it == '.' }

                                    // Parse and enforce max value
                                    val amount = filteredValue.toDoubleOrNull() ?: 0.0
                                    if (amount <= maxAmount) {
                                        if (filteredValue.count { it == '.' } <= 1) {
                                            addViewModel.setAmount(
                                                filteredValue.takeWhile { it.isDigit() || it == '.' }
                                            )

                                        } else addViewModel.setAmount(state.amount)
                                    }
                                },
                                decorationBox = { innerTextField ->
                                    if (state.amount.isEmpty()) {
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

                            Row(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }, // Make Row clickable
                                verticalAlignment = Alignment.CenterVertically // Align Text and Icon
                            ) {
                                // Display the selected text
                                Text(
                                    text = state.recurrence.name,
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
                                     // Dropdown items", "Option 3") // Dropdown items
                                    recurrences.forEach { recurrence ->
                                        DropdownMenuItem(
                                            text = {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Surface(modifier = Modifier.size(14.dp), shape = CircleShape, color = Color.Blue) {  }
                                                    Text(text = recurrence.name, modifier = Modifier.padding(start = 8.dp))
                                                }
                                            },
                                            onClick = {
                                                addViewModel.setRecurrence(recurrence) // Update selected item
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

                            var datePickerShowing by remember {
                                mutableStateOf(false)
                            }

                            Row(
                                modifier = Modifier
                                    .clickable { datePickerShowing = !datePickerShowing }, // Make Row clickable
                                verticalAlignment = Alignment.CenterVertically // Align Text and Icon
                            ){
                                if(state.date.isNotEmpty()){
                                    Text(
                                        text = state.date,
                                        color = ButtonDefaults.buttonColors().containerColor
                                    )
                                }
                                else{
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = "Date Icon",
                                        tint = ButtonDefaults.buttonColors().containerColor,
                                        modifier = Modifier
                                            .padding(start = 4.dp)
                                            .size(20.dp)
                                    )
                                }
                                if(datePickerShowing){
                                    DatePickerDialog(
                                        onDismissRequest = { datePickerShowing = false },
                                        onDateChange = { date ->
                                            addViewModel.setDate(date)
                                            datePickerShowing = false
                                        },
                                        initialDate = LocalDate.parse(state.date, DateTimeFormatter.ofPattern("dd MMM yyyy"))
                                    )
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
                        label = "Notes",
                        content = {
                            BasicTextField(
                                value = state.notes,
                                onValueChange = { newValue: String ->
                                    addViewModel.setNote(newValue)
                                },
                                decorationBox = { innerTextField ->
                                    if (state.notes.isEmpty()) {
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

                            Row(
                                modifier = Modifier
                                    .clickable { expanded = !expanded }, // Make Row clickable
                                verticalAlignment = Alignment.CenterVertically // Align Text and Icon
                            ) {
                                // Display the selected text
                                Text(
                                    text = state.category?.name ?: "Select Category",
                                    style = Typography.bodyMedium,
                                    color = state.category?.color ?: ButtonDefaults.buttonColors().containerColor
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
                                     // Dropdown items", "Option 3") // Dropdown items
                                    state.categories.forEach { category: Category ->
                                        DropdownMenuItem(
                                            text = {
                                                Row(verticalAlignment = Alignment.CenterVertically){
                                                    Surface(modifier = Modifier.size(10.dp), shape = CircleShape, color = state.category!!.color) {  }
                                                    Text(text = category.name, style = Typography.bodyMedium, modifier = Modifier.padding(start = 8.dp))
                                                }
                                            },
                                            onClick = {
                                                addViewModel.setCategory(category) // Update selected item
                                                expanded = false // Close the menu
                                            })
                                    }
                                }
                            }
                        }
                    )
                }
                Button(
                    onClick = {
                        if(state.amount.isNotEmpty() && state.category != null) {
                            addViewModel.addExpense()
                            Toast.makeText(context, "Expense added!", Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(context, "Fill required fields!", Toast.LENGTH_SHORT).show()
                        }
                    },
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