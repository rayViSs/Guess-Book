package com.example.guessbook.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessbook.data.quiz.Book
import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.domain.usecase.AddAnswerUseCase
import com.example.guessbook.domain.usecase.GetBookUseCase
import com.example.guessbook.domain.usecase.GetRandomBooksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizViewModel @Inject constructor(
    private val getRandomBooksUseCase: GetRandomBooksUseCase,
    private val getBookUseCase: GetBookUseCase,
    private val addAnswerUseCase: AddAnswerUseCase
) : ViewModel() {

    private var _threeRandomBooks: MutableLiveData<List<Book>> = MutableLiveData()
    val threeRandomBooks: LiveData<List<Book>> = _threeRandomBooks

    private var _book: MutableLiveData<Book> = MutableLiveData()
    val book: LiveData<Book> = _book

    fun getRandomBooks(book: Book) {
        viewModelScope.launch {
            _threeRandomBooks.postValue(getRandomBooksUseCase.execute(book))
        }
    }

    fun getBook(index: Int) {
        viewModelScope.launch {
            _book.value = getBookUseCase.execute(index)
        }
    }

    fun addAnswer(firstLine: FirstLine) {
        addAnswerUseCase.execute(firstLine)
    }

}


