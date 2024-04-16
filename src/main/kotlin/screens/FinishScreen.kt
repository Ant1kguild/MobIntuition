package screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.unit.dp
import com.mobicon.intuition.intuition.generated.resources.Res
import kotlinx.coroutines.job
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.skia.Data
import org.jetbrains.skia.Rect
import org.jetbrains.skia.skottie.Animation
import org.jetbrains.skia.sksg.InvalidationController
import org.lighthousegames.logging.logging


@Composable
fun FinishScreen() {
    val logger = logging("FinishScreen")

    LottieAnimation(
        modifier = Modifier.fillMaxSize().padding(100.dp),
        animationRoute = "files/star_animation.json",
        restart = false
    )
}



@OptIn(ExperimentalResourceApi::class)
@Composable
fun LottieAnimation(
    modifier: Modifier,
    animationRoute: String,
    restart: Boolean,
    loading: @Composable () -> Unit = {},
) {
    var animation by remember { mutableStateOf<Animation?>(null) }

    LaunchedEffect(Unit) {
        animation = Animation.makeFromData(Data.makeFromBytes(Res.readBytes(animationRoute)))
    }

    when (val value = animation) {
        null -> loading()
        else -> SkiaAnimation(
            modifier = modifier,
            animation = value,
            restart = restart
        )
    }
}

@Composable
private fun SkiaAnimation(
    modifier: Modifier,
    animation: Animation,
    restart: Boolean,
) {
    var elapsedSeconds: Float by remember { mutableStateOf(DefaultElapsedSeconds) }
    var lastTimeInMillis: Long by remember { mutableStateOf(DefaultLastTimeInMillis) }
    val invalidationController = remember { InvalidationController() }




    Surface(
        modifier = modifier
            .drawAnimationOnCanvas(
                animation = animation,
                time = elapsedSeconds,
                invalidationController = invalidationController
            )
    ) {}


    LaunchedEffect(Unit) {
        val scope = this
        while (scope.coroutineContext.job.isActive) {
            withFrameMillis { absoluteMillis ->
                if (lastTimeInMillis > DefaultLastTimeInMillis) {
                    val deltaMillis =
                        (absoluteMillis - lastTimeInMillis)
                    elapsedSeconds += deltaMillis / MillisecondsInOneSecond
                }
                lastTimeInMillis = absoluteMillis
            }

            if (elapsedSeconds >= animation.duration) {
                elapsedSeconds %= animation.duration
            }
        }
    }
}

private fun Modifier.drawAnimationOnCanvas(
    animation: Animation,
    time: Float,
    invalidationController: InvalidationController
): Modifier = this.drawWithContent {
    drawIntoCanvas { canvas ->
        animation.seekFrameTime(time, invalidationController)
        animation.render(
            canvas = canvas.nativeCanvas,
            dst = Rect.makeWH(size.width, size.height),
        )
    }
}

private const val MillisecondsInOneSecond = 1750F
private const val DefaultElapsedSeconds = 0f
private const val DefaultLastTimeInMillis = 0L