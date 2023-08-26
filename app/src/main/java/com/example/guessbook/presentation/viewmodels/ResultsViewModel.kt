package com.example.guessbook.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.domain.usecase.ClearUserAnswersUseCase
import com.example.guessbook.domain.usecase.GetRightAnswersUseCase
import com.example.guessbook.domain.usecase.GetUserAnswersUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val clearUserAnswersUseCase: ClearUserAnswersUseCase,
    private val getRightAnswersUseCase: GetRightAnswersUseCase,
    private val getUserAnswersUseCase: GetUserAnswersUseCase
) : ViewModel() {

    private val _rightAnswersCount: MutableLiveData<Int> = MutableLiveData()
    val rightAnswersCount: LiveData<Int> = _rightAnswersCount

    private val _rightAnswers: MutableLiveData<List<FirstLine>> = MutableLiveData()
    val rightAnswers: LiveData<List<FirstLine>> = _rightAnswers

    private val _userAnswers: MutableLiveData<List<FirstLine>> = MutableLiveData()
    val userAnswers: LiveData<List<FirstLine>> = _userAnswers

    fun getRigthAndSavedAnswers() {
        viewModelScope.launch {
            val userAnswers = getUserAnswersUseCase.execute()
            val rightAnswers = getRightAnswersUseCase.execute()
            _rightAnswers.value = rightAnswers
            _userAnswers.value = userAnswers
            calculateRightAnswersCount()
        }
    }

    fun clearAnswers() {
        clearUserAnswersUseCase.execute()
        _rightAnswers.value = emptyList()
        _userAnswers.value = emptyList()
        _rightAnswersCount.value = 0
    }

    private fun calculateRightAnswersCount() {
        val rightAnswers = _rightAnswers.value
        val userAnswers = _userAnswers.value

        if (rightAnswers != null && userAnswers != null) {
            val rightCount = userAnswers.count { it in rightAnswers }
            _rightAnswersCount.value = rightCount
        }
    }

}
