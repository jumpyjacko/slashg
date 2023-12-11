package com.jumpyjacko.slashg.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.jumpyjacko.slashg.R

data class SettingItem(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsView(navController: NavHostController) {
    val settingsList = remember {
        listOf(
            SettingItem(
                title = "Appearance",
                description = "Theme, Colours",
                icon = Icons.Default.Palette,
                onClick = {},
            ),
            SettingItem(
                title = "Region",
                description = "Language, Units",
                icon = Icons.Default.Language,
                onClick = {},
            ),
            SettingItem(
                title = "About",
                description = "Info, Open-Source Licenses, Links",
                icon = Icons.Default.Info,
                onClick = {},
            )
        )
    }

    Scaffold(
        topBar = { SettingsAppBar(navController) },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        val viewModel = viewModel<SettingsViewModel>()
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            Text(
                text = stringResource(id = R.string.settings) + " (non-functional)", // TODO: Remove "(non-functional)" when settings are functional
                fontSize = MaterialTheme.typography.headlineLarge.fontSize,
                fontStyle = MaterialTheme.typography.headlineLarge.fontStyle,
                modifier = Modifier
                    .padding(top = 48.dp, start = 16.dp)
            )
            LazyColumn(
                modifier = Modifier.padding(8.dp)
            ) {
                items(settingsList.size) { index ->
                    val settingItem = settingsList[index]
                    SettingItemCard(settingItem = settingItem)
                }
            }
        }
    }
}

@Composable
fun SettingItemCard(settingItem: SettingItem) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { settingItem.onClick.invoke() }
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            androidx.compose.material3.Icon(
                imageVector = settingItem.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(32.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = settingItem.title,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                )
                Text(
                    text = settingItem.description,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize,
                )
            }
        }
    }
}