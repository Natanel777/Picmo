package natanel.android.picmo

sealed class Screen(val routh: String) {

    data object WelcomeScreen: Screen("welcome")
    data object RegistrationScreen: Screen("registration")

    data object HomeScreen: Screen("home")
}