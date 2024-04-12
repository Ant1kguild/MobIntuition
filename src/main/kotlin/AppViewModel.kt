import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.bckgr_dark
import com.mobicon.intuition.intuition.generated.resources.bckgr_light
import data.Person
import data.ScreenState
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.lighthousegames.logging.logging

@ExperimentalResourceApi
object AppViewModel {

    private val logger = logging("AppViewModel")
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private val _screenState = MutableStateFlow(ScreenState.Splash)
    val screenState: StateFlow<ScreenState> = _screenState

    private val _selectedPersonFact = MutableStateFlow<Person?>(null)
    val selectedPersonFact : StateFlow<Person?> = _selectedPersonFact

    private val _persons = MutableStateFlow(Person.allPersons())

    val icons: Flow<List<Person>> = _persons.map { list -> list.sortedBy { it.iconPos } }
    val facts: Flow<List<Person>> = _persons.map { list -> list.sortedBy { it.factPos } }

    val backgroundRes = _screenState.map {
        when (it) {
            ScreenState.Splash -> Res.drawable.bckgr_dark
            ScreenState.Users, ScreenState.Facts -> Res.drawable.bckgr_dark
        }
    }


    fun changeScreen(newState: ScreenState) {
        scope.launch {
            logger.info { "changeScreen(newState: $newState)" }
            _screenState.emit(newState)
        }
    }

    fun onSelectPersonFact(person : Person) {
        scope.launch {
            logger.info { "onSelectPersonFact(person: $person)" }
            _selectedPersonFact.emit(person)
            _screenState.emit(ScreenState.Users)
        }
    }

    fun checkPersonFact(person : Person) {
        scope.launch {
            if(person.fact == _selectedPersonFact.value?.fact) {
                _persons.update {list ->
                    val temp = list.toMutableList()
                    temp.remove(person)
                    temp.add(person.copy(guessed = true))
                    temp.sortedBy { it.id }
                }
            }
            delay(100)
            _screenState.emit(ScreenState.Facts)
        }
    }

}