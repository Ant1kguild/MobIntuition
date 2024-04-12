package data

import com.mobicon.intuition.intuition.generated.resources.*
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.ic_alexeyk
import com.mobicon.intuition.intuition.generated.resources.ic_aliaxandrg
import com.mobicon.intuition.intuition.generated.resources.ic_alla
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import java.util.*

@OptIn(ExperimentalResourceApi::class)
data class Person(
    val id : Int,
    val name: String,
    val fact: String,
    val image: DrawableResource,
    val guessed: Boolean,
    val factPos: Int,
    val iconPos: Int
) {
    companion object {

        private val AlexeyKaminsky = Person(
            id = 0,
            name = "Алексей Каминский",
            fact = "Клал борт и тротуарную плитку в Лошицком парке",
            image = Res.drawable.ic_alexeyk,
            guessed = false,
            factPos = 20,
            iconPos = 5
        )

        private val AlexanderGlushen = Person(
            id = 1,
            name = "Александ Глушень",
            fact = "Чет пыхтит",
            image = Res.drawable.ic_aliaxandrg,
            guessed = false,
            factPos = 18,
            iconPos = 2
        )


        private val Alla = Person(
            id = 2,
            name = "Просто алла",
            fact = "Начисляет",
            image = Res.drawable.ic_alla,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val AlyonaSorokina = Person(
            id = 3,
            name = "Алена сорокина",
            fact = "Рисует",
            image = Res.drawable.ic_alyonas,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val AndreyLitvin = Person(
            id = 4,
            name = "Андрей Литвин",
            fact = "Хукает за пуджа",
            image = Res.drawable.ic_andreyl,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val AndreyMishchenko = Person(
            id = 5,
            name = "Андрей Мищенко",
            fact = "Ведущий года",
            image = Res.drawable.ic_andreym,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val ElenaSannikova = Person(
            id = 6,
            name = "Лена Санникова",
            fact = "ОНТ вумэн",
            image = Res.drawable.ic_elenas,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val HannaPrystauka = Person(
            id = 7,
            name = "Анна",
            fact = "Файнд май систер",
            image = Res.drawable.ic_hannap,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val HellenVenichik = Person(
            id = 8,
            name = "Лена Вен",
            fact = "One punch woman",
            image = Res.drawable.ic_hellenv,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val Ihar = Person(
            id = 9,
            name = "Игарек",
            fact = "Вместо ресторана ходил с будущей женой в стриптиз",
            image = Res.drawable.ic_iharm,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val Yara = Person(
            id = 10,
            name = "Юрик",
            fact = "Прокатил бабушку на свинке",
            image = Res.drawable.ic_jurav,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val Kristina = Person(
            id = 11,
            name = "Кристина",
            fact = "Писатель от бога",
            image = Res.drawable.ic_krystina,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val LenaMuc = Person(
            id = 12,
            name = "Лена Мучинская",
            fact = "Подробатывала у деда мороза, маленьким эльфом, упаковывая подарки",
            image = Res.drawable.ic_lenam,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val Masha = Person(
            id = 13,
            name = "Маша",
            fact = "Рыбак рыбака, видит из далека",
            image = Res.drawable.ic_mashau,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val Petr = Person(
            id = 14,
            name = "Петр санников",
            fact = "Знает японский",
            image = Res.drawable.ic_mashau,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val Trutchenko = Person(
            id = 15,
            name = "Саша Трутченко",
            fact = "Пам параммм",
            image = Res.drawable.ic_sashat,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val Slava = Person(
            id = 16,
            name = "Слава Дмитров",
            fact = "Дибошир и балогур, правда только когда был студентом",
            image = Res.drawable.ic_slavad,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        fun allPersons() : List<Person> = listOf(
            AlexeyKaminsky,
            AlexanderGlushen,
            Alla,
            AlyonaSorokina,
            AndreyLitvin,
            AndreyMishchenko,
            ElenaSannikova,
            HannaPrystauka,
            HellenVenichik,
            Ihar,
            Yara,
            Kristina,
            LenaMuc,
            Masha,
            Petr,
            Trutchenko,
            Slava
        )
    }

}
