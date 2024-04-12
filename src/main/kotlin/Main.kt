import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.window.*
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.bckgr_dark
import com.mobicon.intuition.intuition.generated.resources.bckgr_light
import logger.DesktopLogger
import org.lighthousegames.logging.KmLogging
import org.lighthousegames.logging.logging
import screens.FactsScreen
import data.ScreenState
import kotlinx.coroutines.flow.map
import org.jetbrains.compose.resources.painterResource
import screens.SplashScreen
import screens.UsersScreen


@OptIn(ExperimentalResourceApi::class)
fun main() = application {

    KmLogging.setLoggers(DesktopLogger.logger)


    Window(
        onCloseRequest = ::exitApplication,
        state = WindowState(placement = WindowPlacement.Floating),
        title = "Intuition",
    ) {
        val logger = logging("Screen")
        val appViewModel = AppViewModel



        val screen by appViewModel.screenState.collectAsState()

        val background by appViewModel.backgroundRes.collectAsState(Res.drawable.bckgr_light)

        Crossfade(
            targetState = screen,
            modifier = Modifier.fillMaxSize().paint(painter = painterResource(background), contentScale = ContentScale.FillBounds),
            animationSpec = tween(durationMillis = 1000)
        ) {
            when (it) {
                ScreenState.Splash -> SplashScreen(onUserScreen = { appViewModel.changeScreen(ScreenState.Users) })
                ScreenState.Facts -> FactsScreen(appViewModel)
                ScreenState.Users -> UsersScreen(appViewModel)
            }
        }


    }
}
