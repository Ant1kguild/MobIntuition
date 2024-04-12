package screens

import AppViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.splash
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun SplashScreen(onUserScreen: () -> Unit) {

    val image: DrawableResource = Res.drawable.splash
    val icon = painterResource(image)
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = icon,
            modifier = Modifier.fillMaxSize(fraction = 0.5f).align(Alignment.Center).clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { onUserScreen.invoke() }
            ),
            contentDescription = ""
        )
    }


}