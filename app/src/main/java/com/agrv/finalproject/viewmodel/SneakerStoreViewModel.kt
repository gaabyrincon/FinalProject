package com.agrv.finalproject.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.agrv.finalproject.enumerators.ViewModelIDs
import com.agrv.finalproject.model.SneakerOrderState

class SneakerStoreViewModel : ViewModel(){
    var state by mutableStateOf(SneakerOrderState())
        private set


    fun onValue(value:String,textId: String) {
        when(textId) {
            ViewModelIDs.Color.id -> state = state.copy(color = value);
            ViewModelIDs.Quantity.id -> {
                state = state.copy(quantity = value.toInt())
                calculateTotalAmount();
            }
            ViewModelIDs.Price.id -> {
                state = state.copy(price = value.toDouble());
                calculateTotalAmount();
            }
        }

    }

    private fun calculateTotalAmount(){
        val quantity = state.quantity;
        val price = state.price;
        val total = quantity * price;

        state = state.copy(total = total)
    }

    fun reset() {
        state = SneakerOrderState()
    }








}