import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.bckgr_dark
import data.Person
import data.ScreenState
import javazoom.jl.player.Player
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.lighthousegames.logging.logging
import java.io.BufferedInputStream
import java.io.ByteArrayInputStream
import java.io.InputStream


@ExperimentalResourceApi
object AppViewModel {


    private val logger = logging("AppViewModel")
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)


    private val _screenState = MutableStateFlow(ScreenState.Splash)
    val screenState: StateFlow<ScreenState> = _screenState

    private val _selectedPersonFact = MutableStateFlow<Person?>(null)
    val selectedPersonFact: StateFlow<Person?> = _selectedPersonFact

    private val _persons = MutableStateFlow(Person.allPersons())

    val icons: Flow<List<Person>> = _persons.map { list -> list.sortedBy { it.iconPos } }
    val facts: Flow<List<Person>> = _persons.map { list -> list.sortedBy { it.factPos } }

    val backgroundRes = _screenState.map {
        when (it) {
            ScreenState.Splash -> Res.drawable.bckgr_dark
            ScreenState.Users, ScreenState.Facts, ScreenState.Final -> Res.drawable.bckgr_dark
        }
    }


    fun changeScreen(newState: ScreenState) {
        scope.launch { _screenState.emit(newState) }
    }

    fun onSelectPersonFact(person: Person) {
        scope.launch {
            _selectedPersonFact.emit(person)
            _screenState.emit(ScreenState.Users)
        }
    }

    fun checkPersonFact(person: Person) {
        scope.launch {
            val isFactGuessed = person.fact == _selectedPersonFact.value?.fact
            if (isFactGuessed) {
                _persons.update { list ->
                    val temp = list.toMutableList()
                    temp.remove(person)
                    temp.add(person.copy(guessed = true))
                    temp.sortedBy { it.id }
                }
            }
            delay(150)
            val isNotEnd = _persons.value.any { !it.guessed }
            if(isNotEnd) {
                when(isFactGuessed) {
                    true -> playSoundCorrect()
                    false -> playSoundIncorrect()
                }
            }
            delay(800)
            val screen = if (isNotEnd) {
                ScreenState.Facts
            } else {
                playSoundFinal()
                ScreenState.Final
            }
            delay(150)
            _screenState.emit(screen)
        }
    }


    private suspend fun createPlayer(mp3FilePath: String): Player {
        val bufferStream: InputStream = ByteArrayInputStream(Res.readBytes(mp3FilePath))
        val buffer = BufferedInputStream(bufferStream)
        return Player(buffer)
    }
    private fun playSoundCorrect() {
        scope.launch(Dispatchers.Default) {
            val player = createPlayer("files/sound_answer_correct.mp3")
            try {
                player.play()
            } catch (e : Throwable) {
                logger.e { "playSoundCorrect(Error -> ${e.message})" }
            }
        }
    }

    private fun playSoundIncorrect() {
        scope.launch(Dispatchers.Default) {
            val player = createPlayer("files/sound_answer_incorrect.mp3")
            try {
                player.play()
            } catch (e : Throwable) {
                logger.e { "playSoundCorrect(Error -> ${e.message})" }
            }
        }
    }

    private fun playSoundFinal() {
        scope.launch(Dispatchers.Default) {
            val player = createPlayer("files/sound_final.mp3")
            try {
                player.play()
            } catch (e : Throwable) {
                logger.e { "playSoundCorrect(Error -> ${e.message})" }
            }
        }
    }

}