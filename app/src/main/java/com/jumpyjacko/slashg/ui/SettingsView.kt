package com.jumpyjacko.slashg.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(navController: NavHostController) {
    Scaffold(
        topBar = { SlashGAppBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Text(text = "Filler")
    }
}