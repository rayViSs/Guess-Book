package com.example.guessbook.data.repository

import com.example.guessbook.data.quiz.Book
import com.example.guessbook.data.quiz.QuizStorage
import com.example.guessbook.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    override fun getRandomBooks(book: Book): List<Book> {
        val randomBooks = mutableListOf<Book>()
        randomBooks.add(book)
        randomBooks.addAll(getRandomBooksExcept(book))
        return randomBooks.shuffled()
    }

    override fun getBookByIndex(index: Int): Book {
        return QuizStorage.firstLinesForQuestions[index].book
    }

    private fun getRandomBooksExcept(exceptBook: Book, count: Int = 2): List<Book> {
        val availableBooks = QuizStorage.firstLinesForQuestions.filterNot { it.book == exceptBook }
        return (0 until count).map {
            availableBooks.random().book
        }
    }
}