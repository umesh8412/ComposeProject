package com.umesh.funfactscompose.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umesh.funfactscompose.ui.UserInputViewModel


@Composable
fun FunFactsNavGraph(userInputViewModel: UserInputViewModel = viewModel()) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.USER_INPUT_SCREEN) {
        composable(Routes.USER_INPUT_SCREEN) {
            UserInputScreen(userInputViewModel, navController)
        }

        composable("${Routes.WELCOME_SCREEN}/{${Routes.UserName}}/{${Routes.AnimalSelected}}",
            arguments = listOf(
                navArgument(name = Routes.UserName) { type = NavType.StringType },
                navArgument(name = Routes.AnimalSelected) { type = NavType.StringType }
            )
        ) {
            val userName= it.arguments?.getString(Routes.UserName)
            val animalSelected=it.arguments?.getString(Routes.AnimalSelected)
            WelcomeScreen(userName,animalSelected,userInputViewModel)
        }
    }
}