package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import animation.finishLottiAnimation
import io.github.alexzhirkevich.compottie.*

@Composable
fun FinishScreen() {
    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(finishLottiAnimation()))
    val progress = animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        speed = 1.25f
    )
    val painter = rememberLottiePainter(composition, progress.value)

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center).size(1080.dp, 1080.dp)
        )
    }
}