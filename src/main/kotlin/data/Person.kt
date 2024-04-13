package data

import com.mobicon.intuition.intuition.generated.resources.*
import com.mobicon.intuition.intuition.generated.resources.Res
import com.mobicon.intuition.intuition.generated.resources.ic_alexeyk
import com.mobicon.intuition.intuition.generated.resources.ic_aliaxandrg
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

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
            factPos = 7,
            iconPos = 3
        )

        private val AlexanderGlushen = Person(
            id = 1,
            name = "Саша Глушень",
            fact = "Попал в ДТП на мотоцикле на скорости 160",
            image = Res.drawable.ic_aliaxandrg,
            guessed = false,
            factPos = 16,
            iconPos = 6
        )


        private val VikaNaumenko = Person(
            id = 2,
            name = "Вика Науменко",
            fact = "Профессионально делала химчистку машины",
            image = Res.drawable.ic_vikab,
            guessed = false,
            factPos = 1,
            iconPos = 1
        )

        private val AlyonaSorokina = Person(
            id = 3,
            name = "Алена Сорокина",
            fact = "Гасила известь на растущую луну",
            image = Res.drawable.ic_alyonas,
            guessed = false,
            factPos = 6,
            iconPos = 7
        )

        private val AndreyLitvin = Person(
            id = 4,
            name = "Андрей Литвин",
            fact = "В детстве был моделью белорусского трикотажа",
            image = Res.drawable.ic_andreyl,
            guessed = false,
            factPos = 5,
            iconPos = 9
        )

        private val AndreyMishchenko = Person(
            id = 5,
            name = "Андрей Мищенко",
            fact = "Был по локоть в крови спасая человека",
            image = Res.drawable.ic_andreym,
            guessed = false,
            factPos = 3,
            iconPos = 16
        )

        private val ElenaSannikova = Person(
            id = 6,
            name = "Лена Санникова",
            fact = "Работала на ОНТ",
            image = Res.drawable.ic_elenas,
            guessed = false,
            factPos = 15,
            iconPos = 10
        )

        private val HannaPrystauka = Person(
            id = 7,
            name = "Анна Приставко",
            fact = "Нашла сестру через \"Жди меня\"",
            image = Res.drawable.ic_hannap,
            guessed = false,
            factPos = 17,
            iconPos = 14
        )

        private val HellenVenichik = Person(
            id = 8,
            name = "Лена Винничек",
            fact = "Может угостить вкусной домашней выпечкой или апперкотом в челюсть",
            image = Res.drawable.ic_hellenv,
            guessed = false,
            factPos = 13,
            iconPos = 8
        )

        private val IharMakarevich = Person(
            id = 9,
            name = "Игорь Макаревич",
            fact = "Вместо ресторана ходил с будущей невесторй в стриптиз",
            image = Res.drawable.ic_iharm,
            guessed = false,
            factPos = 11,
            iconPos = 12
        )

        private val YuraVaskevich = Person(
            id = 10,
            name = "Юра Васкевич",
            fact = "Когда был маленький прокатил бабушку на свинье",
            image = Res.drawable.ic_jurav,
            guessed = false,
            factPos = 2,
            iconPos = 5
        )

        private val KristinaDanilava = Person(
            id = 11,
            name = "Кристина Данилова",
            fact = "Написала женский роман в стиле фэнтези",
            image = Res.drawable.ic_krystina,
            guessed = false,
            factPos = 10,
            iconPos = 4
        )

        private val LenaMuc = Person(
            id = 12,
            name = "Лена Мучинская",
            fact = "В 17 лет подрабатывала по ночам, упаковывала подарки",
            image = Res.drawable.ic_lenam,
            guessed = false,
            factPos = 14,
            iconPos = 13
        )

        private val MashaUhn = Person(
            id = 13,
            name = "Маша Угнавенок",
            fact = "Поймала щуку на 1 кг",
            image = Res.drawable.ic_mashau,
            guessed = false,
            factPos = 8,
            iconPos = 15
        )

        private val PetrSannikov = Person(
            id = 14,
            name = "Петр Cанников",
            fact = "Изучал японский до Java",
            image = Res.drawable.ic_petrs,
            guessed = false,
            factPos = 9,
            iconPos = 2
        )

        private val VladPavluts = Person(
            id = 15,
            name = "Влад Павлюц",
            fact = "Когда переплывал Днепр, чуть не переехала моторная лодка",
            image = Res.drawable.ic_vladp,
            guessed = false,
            factPos = 12,
            iconPos = 11
        )

        private val Slava = Person(
            id = 16,
            name = "Слава Дмитров",
            fact = "Однажды умение организовывать пьянки чуть не стоило ему места в общаге",
            image = Res.drawable.ic_slavad,
            guessed = false,
            factPos = 4,
            iconPos = 17
        )

        private val VeraVasiukevich = Person(
            id = 17,
            name = "Вера Васюкевич",
            fact = "Любит шампанское с тех пор как бутылка от него порезала ей лицо",
            image = Res.drawable.ic_verav,
            guessed = false,
            factPos = 18,
            iconPos = 0
        )

        fun allPersons() : List<Person> = listOf(
            AlexeyKaminsky,
            AlexanderGlushen,
            VikaNaumenko,
            AlyonaSorokina,
            AndreyLitvin,
            AndreyMishchenko,
            ElenaSannikova,
            HannaPrystauka,
            HellenVenichik,
            IharMakarevich,
            YuraVaskevich,
            KristinaDanilava,
            LenaMuc,
            MashaUhn,
            PetrSannikov,
            VladPavluts,
            Slava,
            VeraVasiukevich
        )
    }

}
