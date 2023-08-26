package com.example.guessbook.domain.usecase

import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.data.repository.AnswersRepositoryImpl
import javax.inject.Inject

class AddAnswerUseCase @Inject constructor(private val answersRepositoryImpl: AnswersRepositoryImpl) {

    fun execute(firstLine: FirstLine) {
        answersRepositoryImpl.addAnswer(firstLine)
    }
}