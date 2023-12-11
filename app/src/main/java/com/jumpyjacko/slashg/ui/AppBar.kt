package com.jumpyjacko.slashg.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.jumpyjacko.slashg.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SlashGAppBar(navController: NavHostController, modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            @Suppress("UNUSED_EXPRESSION")
            null
        },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(Screen.Settings.route) }) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = stringResource(R.string.settings)
                )
            }
        },
        modifier = modifier
    )
}