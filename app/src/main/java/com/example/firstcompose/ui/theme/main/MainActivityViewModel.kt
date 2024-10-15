package com.example.firstcompose.ui.theme.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    // StateFlow ile meyve ve araba listelerini tanımlıyoruz
    private val _fruitList = MutableStateFlow(
        listOf("Apple", "Banana", "Orange", "Strawberry", "Pineapple", "Grapes")
    )
    val fruitList: StateFlow<List<String>> = _fruitList

    private val _carsList = MutableStateFlow(
        listOf("Audi", "BMW", "Bugatti", "Ferrari", "Lamborghini", "Mercedes")
    )
    val carsList: StateFlow<List<String>> = _carsList

    // Listeleri karıştıran fonksiyon
    fun shuffleLists() {
        _fruitList.update { it.shuffled(Random) }
        _carsList.update { it.shuffled(Random) }
    }
}