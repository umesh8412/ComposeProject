package com.umesh.funfactscompose.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.umesh.animalfactscompose.R
import com.umesh.funfactscompose.data.UserDataUiEvents
import com.umesh.funfactscompose.ui.AnimalCard
import com.umesh.funfactscompose.ui.ButtonComponent
import com.umesh.funfactscompose.ui.TextComponent
import com.umesh.funfactscompose.ui.TextFieldComponent
import com.umesh.funfactscompose.ui.TopBar
import com.umesh.funfactscompose.ui.UserInputViewModel

@Composable
fun UserInputScreen(userInputViewModel: UserInputViewModel, navController: NavController) {
    LaunchedEffect(key1 = Unit) {
        userInputViewModel.resetState()
    }
    val localFocusManager = LocalFocusManager.current // Access the LocalFocusManager

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(Unit) {
                    // Detect tap outside and clear focus to dismiss keyboard
                    detectTapGestures {
                        localFocusManager.clearFocus()
                    }
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp, 25.dp)

            ) {
                TopBar("Hii there 😊")
                Spacer(modifier = Modifier.height(16.dp))
                TextComponent(textValue = "Let's Learm about you !", textSize = 25.sp)
                Spacer(modifier = Modifier.height(20.dp))
                TextComponent(
                    textValue = "This app will prepare details based input provided by you.",
                    textSize = 20.sp
                )
                Spacer(modifier = Modifier.height(60.dp))

                TextComponent(textValue = "Name", textSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))

                TextFieldComponent( onTextChanged = {
                    userInputViewModel.onEvent(
                        UserDataUiEvents.UserNameEntered(it)
                    )
                })
                Spacer(modifier = Modifier.height(20.dp))

                TextComponent(textValue = "What do you like", textSize = 20.sp)
                Spacer(modifier = Modifier.height(10.dp))


                Row(modifier = Modifier.fillMaxWidth()) {
                    AnimalCard(image = R.drawable.elephant, animalSelected = {
                        userInputViewModel.onEvent(
                            UserDataUiEvents.AnimalSelected(it)
                        )
                    }, selected = userInputViewModel.uiState.value.animalSelected == "Elephant")
                    AnimalCard(image = R.drawable.horse, animalSelected = {
                        userInputViewModel.onEvent(
                            UserDataUiEvents.AnimalSelected(it)
                        )
                    }, selected = userInputViewModel.uiState.value.animalSelected == "Horse")
                }
                Spacer(modifier = Modifier.weight(1f))

                if (userInputViewModel.isValidState()) {
                    ButtonComponent(
                        goToDetailScreen = {
                            navController.navigate("${Routes.WELCOME_SCREEN}/${userInputViewModel.uiState.value.nameEntered}/${userInputViewModel.uiState.value.animalSelected}")
                        }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun UserInputScreenPreview() {
    // Create a mock NavController using rememberNavController
    val navController = rememberNavController()
    // Pass the NavController and ViewModel to UserInputScreen
    UserInputScreen(userInputViewModel = UserInputViewModel(), navController = navController)
}


