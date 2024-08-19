package natanel.android.picmo.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import natanel.android.picmo.R
import natanel.android.picmo.Screen
import natanel.android.picmo.ui.theme.welcomeFontFamily
import natanel.android.picmo.PreferencesDataStore

@Composable
fun RegistrationScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    val categories = listOf(
        "backgrounds", "fashion", "nature", "science", "education", "feelings", "health", "people",
        "religion", "places", "animals", "industry", "computer", "food", "sports", "transportation",
        "travel", "buildings", "business", "music"
    )
    val selectedCategories = remember { mutableStateListOf<String>() }
    var dropdownExpanded by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    val context = LocalContext.current
    val preferencesDataStore = PreferencesDataStore(context)
    val lifecycleOwner = LocalLifecycleOwner.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFFFFFFF), // White
                        Color(0xFFDCE8F2), // Light Blue
                        Color(0xFFB0C4DE), // Slightly Darker Light Blue
                        Color(0xFF5F6A7D)  // Dark Blue/Grayish
                    ),
                    startY = 0.0f,
                    endY = Float.POSITIVE_INFINITY
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Image at the top of the screen
            Image(
                painter = painterResource(id = R.drawable.picmo_logo_removebg),
                contentDescription = "Registration Image",
                modifier = Modifier
                    .size(150.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = "Create an Account",
                color = Color.DarkGray,
                fontFamily = FontFamily(welcomeFontFamily),
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Username input field
            OutlinedTextField(
                value = username, // Add state to hold the input value
                onValueChange = {
                    username = it
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done // the default icon that shows up in the keyboard
                ),
                label = { Text("Username")},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF006DB5),
                    unfocusedBorderColor = Color.Gray,
                    focusedLabelColor = Color(0xFF006DB5),
                    cursorColor = Color.Black
                )
            )

            // Dropdown menu for categories
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)

            ) {
                OutlinedTextField(
                    value = selectedCategories.joinToString(", "),
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Select Favorite Categories (min 5)") },
                    trailingIcon = {
                        IconButton(onClick = { dropdownExpanded = !dropdownExpanded }) {
                            Icon(
                                imageVector = if (dropdownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.ArrowDropDown,
                                contentDescription = null
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFF006DB5),
                        unfocusedBorderColor = Color.Gray,
                        focusedLabelColor = Color(0xFF006DB5),
                        cursorColor = Color.Black
                    )
                )

                DropdownMenu(
                    expanded = dropdownExpanded,
                    onDismissRequest = { dropdownExpanded = false },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFF1F1F1)) // Updated background color
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp), // Added padding for modern look
                            text = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = category,
                                        modifier = Modifier.weight(1f),
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Medium,
                                            letterSpacing = 0.5.sp
                                        ),
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                    Checkbox(
                                        checked = selectedCategories.contains(category),
                                        onCheckedChange = {
                                            if (it) {
                                                selectedCategories.add(category)
                                            } else {
                                                selectedCategories.remove(category)
                                            }
                                        },
                                        colors = CheckboxDefaults.colors(
                                            checkedColor = Color(0xFF4CAF50), // Cool green color
                                            uncheckedColor = Color(0xFF4CAF50),
                                            checkmarkColor = Color.White
                                        )
                                    )
                                }
                            },
                            onClick = {
                                if (selectedCategories.contains(category)) {
                                    selectedCategories.remove(category)
                                } else {
                                    selectedCategories.add(category)
                                }
                            }
                        )
                    }
                    // Button outside the DropdownMenu, but below the categories dropdown
                    Button(
                        onClick = { dropdownExpanded = false },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50))
                    ) {
                        Text("Done", color = Color.White)
                    }
                }
            }

            // Display error message if exists
            errorMessage?.let {
                Text(
                    text = it,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            // Sign Up button
            Button(
                onClick = {
                    when {
                        username.isEmpty() -> {
                            errorMessage = "Please fill in the username."
                        }
                        selectedCategories.size < 5 -> {
                            errorMessage = "Please select at least 5 categories."
                        }
                        else -> {
                            val categoriesSet = selectedCategories.toSet()
                            lifecycleOwner.lifecycleScope.launch {
                                preferencesDataStore.setWelcomePageLaunch(false)
                                preferencesDataStore.setUsername(username.trim().replaceFirstChar { it.uppercase() })
                                preferencesDataStore.setSelectedCategories(categoriesSet)

                            }

                            //popUpTo - removes the back stack from WelcomeScreen until the current page(Home Page)
                            navController.navigate(Screen.HomeScreen.routh) {
                                popUpTo(Screen.WelcomeScreen.routh)
                                { inclusive = true } // including the WelcomeScreen
                            }
                        }
                    }
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF006DB5))
            ) {
                Text(text = "SIGN UP")
            }

            // Back to Welcome Screen text
            ClickableText(
                onClick = { navController.popBackStack() },
                text = AnnotatedString("Go Back"),
                style = TextStyle(
                    color = Color(0xCC006DB5),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                ),
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun RegistrationScreenPreview() {
    RegistrationScreen(navController = NavController(LocalContext.current))
}