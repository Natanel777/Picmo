package natanel.android.picmo

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.runBlocking
import natanel.android.picmo.ui.theme.PicmoTheme


class MainActivity : ComponentActivity() {

    private lateinit var preferencesDataStore: PreferencesDataStore
    private val isFirstLaunchFlow by lazy { preferencesDataStore.getWelcomePageLaunch().stateIn(lifecycleScope, SharingStarted.Lazily, true) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferencesDataStore = PreferencesDataStore(this)
        val isFirstLaunch = runBlocking { preferencesDataStore.getWelcomePageLaunch().first() } // first value immediately available.

        if (isFirstLaunch) {
            // Welcome screen setup
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(
                    android.graphics.Color.TRANSPARENT,
                    android.graphics.Color.TRANSPARENT
                ),
                navigationBarStyle = SystemBarStyle.dark(
                    android.graphics.Color.TRANSPARENT
                )
            )
        } else {
            // Regular screen setup
            setSystemBarColor(Color.Transparent, Color.Black)
        }


        setContent {
            PicmoTheme {

                //secomd way inside setContent-PicmoTheme->
                val isFirstLaunch2 by isFirstLaunchFlow.collectAsState()

                //effectively configured the status bar and navigation bar to be transparent
                //and ensured that the system bar content (icons and text) is dark
                LaunchedEffect(isFirstLaunch2) {
                    if (isFirstLaunch2) {
                        // Welcome screen setup
                        enableEdgeToEdge(
                            statusBarStyle = SystemBarStyle.light(
                                android.graphics.Color.TRANSPARENT,
                                android.graphics.Color.TRANSPARENT
                            ),
                            navigationBarStyle = SystemBarStyle.dark(
                                android.graphics.Color.TRANSPARENT
                            )
                        )
                    } else {
                        // Regular screen setup
                        setSystemBarColor(Color.Transparent, Color.Black)
                    }
                }
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    Navigation(isFirstLaunch)
                }
            }
        }
    }

    private fun setSystemBarColor(statusBarColor: Color, navigationBarColor: Color) {
        val window = window
        window.statusBarColor = statusBarColor.toArgb()
        window.navigationBarColor = navigationBarColor.toArgb()
        WindowCompat.setDecorFitsSystemWindows(window, true)
    }
}


//Not EdgeToEdge
@Composable
private fun SetBarColor(topBarColor: Color, navigationColor: Color) {
    val view = LocalView.current

    if (!view.isInEditMode) {
        LaunchedEffect(key1 = true) {
            val window = (view.context as Activity).window
            window.statusBarColor = topBarColor.toArgb()
            window.navigationBarColor = navigationColor.toArgb()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                window.setDecorFitsSystemWindows(false)
                val controller = window.insetsController
                if (controller != null) {
                    controller.hide(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE)
                    controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                }
            } else {
                @Suppress("DEPRECATION")
                window.decorView.systemUiVisibility = (
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                                View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        )
            }
        }
    }
}



