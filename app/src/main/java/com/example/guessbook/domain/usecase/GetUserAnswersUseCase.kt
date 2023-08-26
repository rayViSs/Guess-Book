package com.example.guessbook.domain.usecase

import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.data.repository.AnswersRepositoryImpl
import javax.inject.Inject

class GetUserAnswersUseCase @Inject constructor(private val answersRepositoryImpl: AnswersRepositoryImpl) {

    fun execute(): List<FirstLine>{
        return answersRepositoryImpl.getUserAnswers()
    }
}