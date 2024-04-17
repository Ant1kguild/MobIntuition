package sound

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope

@Composable
internal fun rememberSoundController(filePath: String): SoundController {
    val scope = rememberCoroutineScope()

    val controller = remember { SoundController(scope, filePath) }
    controller.path.value = filePath // Update filePath last so all other properties are up to date.
    controller.onRemembered() // Invoke this manually so `painter.state` is set to `Loading` immediately.

    return controller
}