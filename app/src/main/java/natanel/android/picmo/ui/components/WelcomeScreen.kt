package natanel.android.picmo.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import natanel.android.picmo.R
import natanel.android.picmo.Screen
import natanel.android.picmo.ui.theme.welcomeFontFamily


@Composable
fun WelcomeScreen(navController: NavController) {
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

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Welcome to",
                    color = Color.DarkGray,
                    fontFamily = FontFamily(welcomeFontFamily),
                    fontSize = 40.sp,
                    textAlign = TextAlign.Center
                )

                Text(
                    text = "Picmo!",
                    color = Color.DarkGray,
                    fontFamily = FontFamily(welcomeFontFamily),
                    fontSize = 40.sp,
                    modifier = Modifier.padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            Text(
                text = "Capture and share your moments effortlessly",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Default,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            // Image in the middle of the screen
            Image(
                painter = painterResource(id = R.drawable.picmo_logo_removebg),
                contentDescription = "Welcome Image",
                modifier = Modifier
                    .size(300.dp)
                    .padding(bottom = 32.dp),
                contentScale = ContentScale.Fit
            )
            // Sign Up button
            Button(
                onClick = {
                    navController.navigate(Screen.RegistrationScreen.routh)
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFF006DB5))
            ) {
                Text(text = "START")
            }
            // Log In button

            Button(
                onClick = { /* TODO: Handle Log In click */ },
                modifier = Modifier
                    .width(300.dp)
                    .height(50.dp)
                    .padding(bottom = 8.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                border = BorderStroke(1.dp, Color(0xFF006DB5))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.google), // replace with your image resource
                        contentDescription = "Google Logo",
                        modifier = Modifier
                            .size(24.dp)
                            .padding(end = 8.dp)
                    )
                    Text(text = "SIGN UP WITH GOOGLE", color = Color(0xFF006DB5))
                }
            }
//            // Join As A Guest text
//            ClickableText(
//                onClick = { Log.d("ClickableText", "CLICKED!") },
//                text = AnnotatedString("Join As A Guest"),
//                style = TextStyle(
//                    color = Color(0xCC006DB5),
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp,
//                ),
//                modifier = Modifier.padding(top = 5.dp)
//            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun WelcomeScreenPreview(){
    WelcomeScreen(navController = NavController(LocalContext.current))
}