package natanel.android.picmo

import android.content.Context
import android.util.Log
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

private const val PREFERENCES_NAME = "my_app_prefs"
private const val TAG = "PreferencesDataStore"

class PreferencesDataStore(private val context: Context) {

    companion object {
        private val Context.dataStore by preferencesDataStore(name = PREFERENCES_NAME)
        private val LAUNCH_WELCOME_PAGE = booleanPreferencesKey("launch_welcome_page")
        private val USERNAME = stringPreferencesKey("username")
        private val SELECTED_CATEGORIES = stringSetPreferencesKey("selected_categories")
    }

// --IS_FIRST_LAUNCH Section

    // Set firstLaunch to true / false
    suspend fun setWelcomePageLaunch(isFirstLaunch: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[LAUNCH_WELCOME_PAGE] = isFirstLaunch
        }
    }

    // Check if the app First Launched
    fun getWelcomePageLaunch(): Flow<Boolean> {
        return context.dataStore.data
            .catch { e ->
                if (e is IOException) {
                    Log.e(TAG, "Error reading preferences.", e)
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }
            .map { preferences ->
                preferences[LAUNCH_WELCOME_PAGE] ?: true
            }
    }

// --IS_FIRST_LAUNCH Section

    suspend fun setUsername(username: String) {
        context.dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    // Check if the app First Launched
    fun getUsername(): Flow<String> {
        return context.dataStore.data
            .catch { e ->
                if (e is IOException) {
                    Log.e(TAG, "Error reading preferences.", e)
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }
            .map { preferences ->
                preferences[USERNAME] ?: ""
            }
    }

// --SELECTED_CATEGORIES Section

    suspend fun setSelectedCategories(categories: Set<String>) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CATEGORIES] = categories
        }
    }

    fun getSelectedCategories(): Flow<Set<String>> {
        return context.dataStore.data
            .catch { e ->
                if (e is IOException) {
                    Log.e(TAG, "Error reading preferences.", e)
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }
            .map { preferences ->
                preferences[SELECTED_CATEGORIES] ?: emptySet()
            }
    }

    // Get all preferences
    fun getAllPreferences(): Flow<Map<String, Any>> {
        return context.dataStore.data
            .catch { e ->
                if (e is IOException) {
                    Log.e(TAG, "Error reading preferences.", e)
                    emit(emptyPreferences())
                } else {
                    throw e
                }
            }
            .map { preferences ->
                mapOf(
                    "isFirstLaunch" to (preferences[LAUNCH_WELCOME_PAGE] ?: true),
                    "username" to (preferences[USERNAME] ?: ""),
                    "selectedCategories" to (preferences[SELECTED_CATEGORIES] ?: emptySet())
                )
            }
    }
}