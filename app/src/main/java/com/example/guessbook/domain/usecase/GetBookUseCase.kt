package com.example.guessbook.domain.usecase

import com.example.guessbook.data.quiz.Book
import com.example.guessbook.data.repository.BookRepositoryImpl
import javax.inject.Inject

class GetBookUseCase @Inject constructor(private val bookRepositoryImpl: BookRepositoryImpl) {

    fun execute(index: Int): Book {
        return bookRepositoryImpl.getBookByIndex(index)
    }
}