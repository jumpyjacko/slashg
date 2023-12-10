package com.jumpyjacko.slashg

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.jumpyjacko.slashg.ui.CalculationView
import com.jumpyjacko.slashg.ui.theme.SlashGTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SlashGTheme {
                CalculationView()
            }
        }
    }
}