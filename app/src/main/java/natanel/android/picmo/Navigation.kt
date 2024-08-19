package natanel.android.picmo

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import natanel.android.picmo.ui.components.HomeScreen
import natanel.android.picmo.ui.components.RegistrationScreen
import natanel.android.picmo.ui.components.WelcomeScreen

@Composable
fun Navigation(isFirstLaunch: Boolean) {
    val navController = rememberNavController()
    println("navigation isFirstLaunchFlow: $isFirstLaunch")

    NavHost(
        navController = navController,
        startDestination =
        if (isFirstLaunch)
            Screen.WelcomeScreen.routh
        else
            Screen.HomeScreen.routh
    ) {

        // Welcome Screen
        composable(Screen.WelcomeScreen.routh) {
            WelcomeScreen(navController = navController)
        }

        // Registration Screen
        composable(Screen.RegistrationScreen.routh){
            RegistrationScreen(navController = navController)
        }

        // Home Screen
        composable(Screen.HomeScreen.routh){
            HomeScreen()
        }


    }
}