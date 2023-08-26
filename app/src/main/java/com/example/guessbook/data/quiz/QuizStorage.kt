package com.example.guessbook.data.quiz

import com.example.guessbook.R


object QuizStorage {

    val userAnswers = mutableListOf<FirstLine>()

    private val allBooks = listOf(
        Book(
            "Это было самое прекрасное время, это было самое злосчастное время",
            R.drawable.povest_o_dvuh_gorodah
        ),
        Book(
            "Все счастливые семьи похожи друг на друга, каждая несчастливая семья несчастлива по-своему",
            R.drawable.anna_karenina
        ),
        Book(
            "Прошлое — это другая страна: там все иначе",
            R.drawable.posrednik
        ),
        Book(
            "Все знают, что молодой человек, располагающий средствами, должен подыскивать себе жену",
            R.drawable.gordost_i_predubezhdenie
        ),
        Book(
            "У повести нет ни начала, ни конца, и мы произвольно выбираем миг, из которого смотрим вперед или назад",
            R.drawable.konets_odnogo_romana
            ),
        Book(
            "В этот день нечего было и думать о прогулке",
            R.drawable.dzhejn_ejr
            ),
        Book(
            "Никто не умер в этом году",
            R.drawable.tajnaya_istoriya
        ),
        Book(
            "Прошлой ночью мне снилось, что я вернулась в Мандерли",
            R.drawable.rebekka_sbornik
            ),
        Book(
            "Я пишу это, сидя в кухонной раковине",
            R.drawable.ya_zahvatyvayu_zamok
            ),
        Book(
            "Я человек больной… Я злой человек. Непривлекательный я человек. Я думаю, что у меня болит печень",
            R.drawable.zapiski_iz_podpolya
            ),
    )

    val firstLinesForQuestions = allBooks.mapIndexed { index, book ->
        FirstLine(index, book)
    }
}
