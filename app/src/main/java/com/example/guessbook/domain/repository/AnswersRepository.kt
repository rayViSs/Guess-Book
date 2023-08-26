package com.example.guessbook.domain.repository

import com.example.guessbook.data.quiz.FirstLine

interface AnswersRepository {

    fun addAnswer(firstLine: FirstLine)

    fun getUserAnswers(): List<FirstLine>

    fun getRightAnswers(): List<FirstLine>

    fun clearUserAnswers()
}