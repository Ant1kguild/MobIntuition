package data



sealed interface ScreenState {
    data object Splash : ScreenState
    data object Facts : ScreenState
    data object Users : ScreenState
    data object Final : ScreenState
    data class FactProf(val person: Person) : ScreenState
}