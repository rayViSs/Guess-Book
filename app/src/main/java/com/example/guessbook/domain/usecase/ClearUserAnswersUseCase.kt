package com.example.guessbook.domain.usecase

import com.example.guessbook.data.repository.AnswersRepositoryImpl
import javax.inject.Inject

class ClearUserAnswersUseCase @Inject constructor(private val answersRepositoryImpl: AnswersRepositoryImpl) {

    fun execute() {
        return answersRepositoryImpl.clearUserAnswers()
    }
}