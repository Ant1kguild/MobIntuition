package screens

import AppViewModel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import animation.lottieData
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.logo
import data.Person
import data.ScreenState
import io.github.alexzhirkevich.compottie.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.lighthousegames.logging.logging
import sound.rememberSoundController


@OptIn(ExperimentalResourceApi::class)
@Composable
fun FactProfScreen(vm: AppViewModel, person: Person) {

    val logger = logging("SplashScreen")

    val icon = painterResource(person.factPh!!)




    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {


        Image(
            painter = icon,
            modifier = Modifier
                .fillMaxSize(fraction = 0.85f)
                .align(Alignment.Center),
            contentDescription = ""
        )
    }

    LaunchedEffect(Unit) {
        launch(Dispatchers.Default) {
            delay(4000)
            vm.changeScreen(ScreenState.Facts)
        }
    }
}





