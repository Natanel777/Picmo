package natanel.android.picmo

import androidx.annotation.DrawableRes
import androidx.compose.ui.Modifier

data class PinCardData(
    val authorName: String,
    @DrawableRes val imageId: Int,
    @DrawableRes val authorImage: Int,
)

