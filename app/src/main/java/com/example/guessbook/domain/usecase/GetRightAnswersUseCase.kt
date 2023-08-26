package com.example.guessbook.domain.usecase

import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.data.repository.AnswersRepositoryImpl
import javax.inject.Inject

class GetRightAnswersUseCase @Inject constructor(private val answersRepositoryImpl: AnswersRepositoryImpl) {

    fun execute(): List<FirstLine>{
        return answersRepositoryImpl.getRightAnswers()
    }
}