package com.example.guessbook.data.repository

import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.data.quiz.QuizStorage
import com.example.guessbook.domain.repository.AnswersRepository
import javax.inject.Inject

class AnswersRepositoryImpl @Inject constructor() : AnswersRepository {

    override fun addAnswer(firstLine: FirstLine) {
        QuizStorage.userAnswers.add(firstLine)
    }

    override fun getUserAnswers(): List<FirstLine> {
        return QuizStorage.userAnswers
    }

    override fun getRightAnswers(): List<FirstLine> {
        return QuizStorage.firstLinesForQuestions
    }

    override fun clearUserAnswers() {
        QuizStorage.userAnswers.clear()
    }
}