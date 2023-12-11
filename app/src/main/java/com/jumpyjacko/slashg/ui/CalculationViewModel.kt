package com.jumpyjacko.slashg.ui

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CalculationViewModel : ViewModel() {
    var savedItems = mutableStateListOf<Double>()
        private set
    private var _priceInput: MutableState<String> = mutableStateOf("")
    var priceInput: String
        get() = _priceInput.value
        set(value) {
            _priceInput.value = value
        }

    private var _weightInput: MutableState<String> = mutableStateOf("")
    var weightInput: String
        get() = _weightInput.value
        set(value) {
            _weightInput.value = value
        }

    fun addToSavedItems(value: Double) {
        if (value == 0.0) return
        savedItems.add(value)
        priceInput = ""
        weightInput = ""
    }

    fun clearSavedItems() {
        savedItems.clear()
    }
}
