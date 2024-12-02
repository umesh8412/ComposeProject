package com.umesh.funfactscompose.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.umesh.funfactscompose.ui.FactComposable
import com.umesh.funfactscompose.ui.TextComponent
import com.umesh.funfactscompose.ui.TopBar
import com.umesh.funfactscompose.ui.UserInputViewModel

@Composable
fun WelcomeScreen(userName: String?, animalSelected: String?,viewModel: UserInputViewModel) {
    println("$userName and $animalSelected")
    val randomFact = viewModel.getRandomFactForAnimal()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(18.dp, 25.dp)
        ) {
            val emoji = when (animalSelected) {
                "Horse" -> "🐎"  // Horse emoji for horse lovers
                "Elephant" -> "🐘"  // Elephant emoji for elephant lovers
                else -> ""  // No emoji for other animals
            }
            TopBar("Welcome ${userName} 😍")
            Spacer(modifier = Modifier.height(40.dp))

            TextComponent(textValue = "Thank you sharing the data.", textSize = 24.sp)
            Spacer(modifier = Modifier.height(70.dp))

            TextComponent(
                textValue = "You are ${animalSelected} Lover $emoji",
                textSize = 25.sp,
                fontWeight = FontWeight.Normal
            )
            FactComposable(value = randomFact)
        }


    }
}

@Preview
@Composable
fun WelcomScreenPreview() {
    WelcomeScreen("Umesh", "Horse", viewModel())
}