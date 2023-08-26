package com.example.guessbook.domain.repository

import com.example.guessbook.data.quiz.Book

interface BookRepository {

    fun getRandomBooks(book: Book) : List<Book>

    fun getBookByIndex(index: Int): Book

}