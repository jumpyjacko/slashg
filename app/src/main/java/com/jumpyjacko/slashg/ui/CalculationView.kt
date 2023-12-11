package com.jumpyjacko.slashg.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jumpyjacko.slashg.R
import com.jumpyjacko.slashg.utils.round

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CalculationView(
    navController: NavHostController,
) {
    val mainTextSizes = 24
    val textFieldWidth = 116

    Scaffold(
        topBar = { SlashGAppBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val viewModel = viewModel<CalculationViewModel>()

        val price = viewModel.priceInput.toDoubleOrNull() ?: 0.0
        val weight = viewModel.weightInput.toDoubleOrNull() ?: 0.0
        val calculatedValue = ((price / weight) * 100.0).round(2)
        val cleanedCalculatedValue =
            if (calculatedValue.isNaN() || calculatedValue.isInfinite()) 0.0 else calculatedValue
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth()
            ) {
                Text(text = "$", fontSize = mainTextSizes.sp)
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = viewModel.priceInput,
                    onValueChange = {
                        viewModel.priceInput = it
                    },
                    textStyle = TextStyle(
                        fontSize = mainTextSizes.sp,
                        textAlign = TextAlign.Center
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { viewModel.addToSavedItems(cleanedCalculatedValue) }
                    ),
                    modifier = Modifier.width(textFieldWidth.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "/", fontSize = mainTextSizes.sp)
                Spacer(modifier = Modifier.width(8.dp))
                TextField(
                    value = viewModel.weightInput,
                    onValueChange = {
                        viewModel.weightInput = it
                    },
                    textStyle = TextStyle(
                        fontSize = mainTextSizes.sp,
                        textAlign = TextAlign.Center
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { viewModel.addToSavedItems(cleanedCalculatedValue) }
                    ),
                    modifier = Modifier.width(textFieldWidth.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "g", fontSize = mainTextSizes.sp)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "$$cleanedCalculatedValue / 100g")
            Spacer(modifier = Modifier.height(mainTextSizes.dp))
            Button(onClick = {
                viewModel.addToSavedItems(cleanedCalculatedValue)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(R.string.add_to_list)
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = stringResource(R.string.saved_items),
                fontStyle = FontStyle.Italic,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            LazyColumn(
                reverseLayout = true,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .height(200.dp)
            ) {
                items(viewModel.savedItems) { item ->
                    Text(text = "$$item / 100g")
                }
            }
            FilledTonalButton(onClick = { viewModel.clearSavedItems() }) {
                Text(text = stringResource(id = R.string.clear_list))
            }
        }
    }
}