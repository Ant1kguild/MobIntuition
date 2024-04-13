package screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import animation.lottieData
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.logo
import io.github.alexzhirkevich.compottie.*
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(onUserScreen: () -> Unit) {

    val image: DrawableResource = Res.drawable.logo
    val icon = painterResource(image)
    val interactionSource = remember { MutableInteractionSource() }


    val composition by rememberLottieComposition(LottieCompositionSpec.JsonString(lottieData))
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
            modifier = Modifier.fillMaxSize()
        )

        Image(
            painter = icon,
            modifier = Modifier.fillMaxSize(fraction = 0.65f).align(Alignment.Center).clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onUserScreen.invoke() }
            ),
            contentDescription = ""
        )
    }


}