package com.umesh.funfactscompose.data

sealed class UserDataUiEvents{
    data class UserNameEntered(val name:String):UserDataUiEvents()
    data class AnimalSelected(val animalValue:String):UserDataUiEvents()
}