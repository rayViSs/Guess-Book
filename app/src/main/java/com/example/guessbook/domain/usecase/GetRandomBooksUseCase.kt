package com.example.guessbook.domain.usecase

import com.example.guessbook.data.quiz.Book
import com.example.guessbook.data.repository.BookRepositoryImpl
import javax.inject.Inject

class GetRandomBooksUseCase @Inject constructor(private val booksRepositoryImpl: BookRepositoryImpl) {

    fun execute(book: Book): List<Book>{
        return booksRepositoryImpl.getRandomBooks(book)
    }
}