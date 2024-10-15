package com.example.firstcompose.ui.theme.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.firstcompose.ui.theme.FirstComposeTheme
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FirstComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ListScreen(modifier: Modifier, viewModel: MainActivityViewModel = MainActivityViewModel()) {
    // LiveData'yı observe ederek UI'a bağlama
    val fruitList by viewModel.fruitList.collectAsState()
    val carsList by viewModel.carsList.collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {
            // Yatayda iki sütunu hizalamak için Row kullanıyoruz
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween // Sütunlar arası boşluk
            ) {
                // İlk sütun: Meyve listesi
                LazyColumn(
                    modifier = Modifier.weight(1f), // Eşit genişlik için
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Elemanlar arası boşluk
                ) {
                    items(fruitList) { fruit ->
                        Text(text = fruit)
                    }
                }

                // İkinci sütun: Araba listesi
                LazyColumn(
                    modifier = Modifier.weight(1f), // Eşit genişlik için
                    verticalArrangement = Arrangement.spacedBy(8.dp) // Elemanlar arası boşluk
                ) {
                    items(carsList) { car ->
                        Text(text = car)
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            // Karıştırma butonu
            Button(onClick = {
                viewModel.shuffleLists() // Listeleri karıştır
            }) {
                Text(
                    text = "Karıştır",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GreetingPreview() {
    FirstComposeTheme {
        ListScreen(modifier = Modifier.padding())
    }
}