package sound

import androidx.compose.runtime.*
import com.mobicon.intuition.intuition.generated.resources.Res
import javazoom.jl.player.Player
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.lighthousegames.logging.logging
import utils.atomic
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.InputStream

@OptIn(ExperimentalResourceApi::class)
@Stable
internal class SoundController(private val rememberScope: CoroutineScope, filePath: String) : RememberObserver {
    private val logger = logging("SoundController")
    private var isPlay: Boolean by atomic(false)
    private var mp3Player: Player? by mutableStateOf(null)
    private var _mp3Player: Player? = null
        set(value) {
            field = value
            mp3Player = value
        }

    var path = MutableStateFlow(filePath)

    init {
        // Observe the current request and execute any emissions.
        path
            .map { createPlayer(it) }
            .onEach { updateMp3Player(it) }
            .launchIn(rememberScope)
    }


    fun playSound() {
        rememberScope.launch(Dispatchers.Default) {
            if(!isPlay) {
                try {
                    isPlay = true
                    mp3Player?.play()
                    val isComplete = mp3Player?.isComplete ?: true
                    isPlay = isComplete.not()
                } catch (e: Throwable) {
                    logger.e { "playSound(Error -> ${e.message})" }
                }
            }
        }
    }

    fun stopPlaySound() {
        rememberScope.launch(Dispatchers.Default) {
            when(isPlay) {
                true -> {
                    mp3Player?.close()
                    _mp3Player?.close()
                    _mp3Player= null
                    isPlay = false
                }
                false -> {
                    _mp3Player= null
                    isPlay = false
                }
            }
            updateMp3Player(createPlayer(path.value))
        }
    }

    override fun onAbandoned() {
        logger.info { "onAbandoned()" }
        clear()
    }

    override fun onForgotten() {
        clear()
    }


    override fun onRemembered() {

    }

    private suspend fun createPlayer(mp3FilePath: String): Player {
        val bufferStream: InputStream = ByteArrayInputStream(Res.readBytes(mp3FilePath))
        val buffer = BufferedInputStream(bufferStream)
        return Player(buffer)
    }

    private fun updateMp3Player(player: Player) {
        mp3Player?.close()
        _mp3Player?.close()
        _mp3Player = player
    }

    private fun clear() {
        _mp3Player?.close()
        _mp3Player = null
        rememberScope.cancel()
    }

}