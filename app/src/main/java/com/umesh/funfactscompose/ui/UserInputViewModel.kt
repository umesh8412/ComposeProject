package com.umesh.funfactscompose.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.umesh.funfactscompose.data.UserDataUiEvents
import com.umesh.funfactscompose.data.UserInputScreenState

class UserInputViewModel : ViewModel() {
    companion object{
        const val  TAG="UserInputViewModel"
    }
    var uiState = mutableStateOf(UserInputScreenState())
    fun onEvent(event:UserDataUiEvents){
        when(event){
            is UserDataUiEvents.UserNameEntered ->{
                uiState.value=uiState.value.copy(
                    nameEntered = event.name
                )
                Log.d(TAG,"onEvents.UserNameEntered")
                Log.d(TAG,"${uiState.value}")
            }
            is UserDataUiEvents.AnimalSelected ->{
                uiState.value=uiState.value.copy(
                    animalSelected = event.animalValue
                )
                Log.d(TAG,"onEvents.AnimalSelected")
                Log.d(TAG,"${uiState.value}")
            }

        }
    }

    fun isValidState():Boolean{
        return !uiState.value.nameEntered.isNullOrEmpty() && !uiState.value.animalSelected.isNullOrEmpty()
    }

    fun resetState() {
        uiState.value = UserInputScreenState()
    }
    // Store facts for different animals
    private val animalFacts = mapOf(
        "Horse" to listOf(
            "The horse can run faster than the human mind.",
            "Horses can sleep both lying down and standing up.",
            "A horse's vision is almost 360 degrees."
        ),
        "Elephant" to listOf(
            "Elephants are the largest land animals on Earth.",
            "Elephants have a highly developed brain and show empathy.",
            "An elephant's trunk contains over 40,000 muscles."
        ),
    )

    // Get a random fact based on the selected animal
    fun getRandomFactForAnimal(): String {
        val selectedAnimal = uiState.value.animalSelected
        val facts = animalFacts[selectedAnimal]
        return facts?.random() ?: "No facts available for this animal."
    }
}




