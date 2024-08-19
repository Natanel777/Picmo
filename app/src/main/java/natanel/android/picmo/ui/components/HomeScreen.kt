package natanel.android.picmo.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.ui.unit.sp
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import natanel.android.picmo.PinCardData
import natanel.android.picmo.PreferencesDataStore
import natanel.android.picmo.R

@Composable
fun HomeScreen() {
    HomeScreenPreview()
}

@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview() {
//    val itemsList = listOf(
//        R.drawable.chess_vertical,
//        R.drawable.nature_horizontal,
//        R.drawable.designer,
//        R.drawable.girl_normal,
//        R.drawable.chess_vertical,
//        R.drawable.nature_horizontal,
//        R.drawable.designer,
//        R.drawable.girl_normal
//    )

    val itemsList = listOf(
        PinCardData(
            authorName = "Chess Master",
            imageId = R.drawable.chess_vertical,
            authorImage = R.drawable.google
        ),
        PinCardData(
            authorName = "Nature Lover",
            imageId = R.drawable.nature_horizontal,
            authorImage = R.drawable.picmo_logo
        ),
        PinCardData(
            authorName = "Creative Designer",
            imageId = R.drawable.designer,
            authorImage = R.drawable.logo_icon
        ),
        PinCardData(
            authorName = "Portrait Artist",
            imageId = R.drawable.girl_normal,
            authorImage = R.drawable.picmo_animal_tansparent
        ),
        PinCardData(
            authorName = "Chess Master",
            imageId = R.drawable.chess_vertical,
            authorImage = R.drawable.google
        ),
        PinCardData(
            authorName = "Nature Lover",
            imageId = R.drawable.nature_horizontal,
            authorImage = R.drawable.picmo_logo
        ),
        PinCardData(
            authorName = "fernandozhiminaicela",
            imageId = R.drawable.designer,
            authorImage = R.drawable.logo_icon
        ),
        PinCardData(
            authorName = "Portrait Artist",
            imageId = R.drawable.girl_normal,
            authorImage = R.drawable.picmo_animal_tansparent
        )
    )
    //LazyVerticalStaggeredGrid -> for images with different height and width
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(2),
    ) {
        items(itemsList) { item ->
            PinCard(item.authorName, item.imageId, item.authorImage)
            //DrawableComp(image)
        }
    }
}

@Composable
fun PinCard(
    authorName: String,
    @DrawableRes imageId: Int,
    @DrawableRes authorImage: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        //Card like in XML
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = imageId),
                contentDescription = ""
            )
        }


        Row(modifier = Modifier.fillMaxWidth()) {

            // Author Image
            Image(
                painter = painterResource(id = authorImage),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(24.dp)
                    .clip(RoundedCornerShape(50.dp)),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column {

                // Author Name
                Text(
                    text = if (authorName.length > 16) authorName.take(16) + "..." else authorName,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(5.dp)
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {
                // "More" Icon
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(18.dp)
                        .align(Alignment.End)
                        .graphicsLayer {
                            rotationZ = 90f
                        },
                )
            }

        }
    }
}

@Composable
fun DrawableComp(@DrawableRes id: Int) {
    Column {
//Card like in XML
        ElevatedCard(
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp
            ),
            modifier = Modifier.padding(5.dp)
        ) {
            Image(
                painter = painterResource(id = id),
                contentDescription = ""
            )
        }


        Row(modifier = Modifier.fillMaxWidth()) {


            Image(
                painter = painterResource(id = R.drawable.picmo_logo),
                modifier = Modifier
                    .padding(start = 5.dp)
                    .size(width = 30.dp, height = 30.dp)
                    .clip(RoundedCornerShape(50.dp)),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column {
                Text(
                    text = "Alfred_Grupstr",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black,
                    textAlign = TextAlign.Start,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
            Column(modifier = Modifier.fillMaxWidth()) {

                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "more",
                    modifier = Modifier
                        .padding(6.dp)
                        .size(18.dp)
                        .align(Alignment.End)
                        .graphicsLayer {
                            rotationZ = 90f
                        },
                )
            }

        }
    }

}


//--------------------------------------------------------------------------------
@Composable
fun PreviousHomeScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val preferencesDataStore = PreferencesDataStore(context)
    val allPreferences by preferencesDataStore.getAllPreferences()
        .collectAsState(initial = emptyMap())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = "Home Screen",
            fontSize = 40.sp,
            modifier = Modifier.padding(bottom = 16.dp, top = 16.dp)
        )

        Text(
            text = "Preferences:",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

//        allPreferences.forEach { (key, value) ->
//            Text(
//                text = "$key: $value",
//                fontSize = 16.sp,
//                modifier = Modifier.padding(bottom = 4.dp)
//            )
//        }

        Button(
            modifier = Modifier.padding(top = 10.dp),
            onClick = {
                lifecycleOwner.lifecycleScope.launch {
                    preferencesDataStore.setWelcomePageLaunch(true)
                }
            }) {
            Text(
                text = "Delete Preferences",
                fontSize = 20.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        LazyRow {
            items(5) {
                LazyColumn {
                    items(20) {
                        VectorComp()
                    }
                }
            }
        }

    }
}

@Composable
fun VectorComp() {
    Icon(
        imageVector = Icons.Default.CheckCircle,
        contentDescription = "add",
        modifier = Modifier.size(60.dp)
    )
}