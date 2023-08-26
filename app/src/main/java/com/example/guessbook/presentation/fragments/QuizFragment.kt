package com.example.guessbook.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.guessbook.R
import com.example.guessbook.data.quiz.Book
import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.data.quiz.QuizStorage
import com.example.guessbook.databinding.FragmentQuizBinding
import com.example.guessbook.presentation.viewmodels.QuizViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!

    private val quizViewModel: QuizViewModel by viewModels()

    private lateinit var selectedBooks: List<Book>
    private var questionCount = 0

    companion object {
        fun newInstance() = QuizFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        quizViewModel.book.observe(viewLifecycleOwner) { book ->
            binding.firstTitleTextview.text = book.firstLine
            quizViewModel.getRandomBooks(book)
        }

        quizViewModel.threeRandomBooks.observe(viewLifecycleOwner) { books ->
            binding.imgCoverBook1.setBackgroundResource(books[0].bookCover)
            binding.imgCoverBook2.setBackgroundResource(books[1].bookCover)
            binding.imgCoverBook3.setBackgroundResource(books[2].bookCover)

            binding.imgCoverBook1.setOnClickListener { onChoiceButtonClicked(0) }
            binding.imgCoverBook2.setOnClickListener { onChoiceButtonClicked(1) }
            binding.imgCoverBook3.setOnClickListener { onChoiceButtonClicked(2) }
        }

        getBook()
    }

    private fun getBook() {
        quizViewModel.getBook(questionCount)
    }

    private fun onChoiceButtonClicked(index: Int) {
        val selectedBook = quizViewModel.threeRandomBooks.value?.get(index)
        val answer = selectedBook?.let { FirstLine(questionCount, it) }

        answer?.let {
            quizViewModel.addAnswer(answer)
            questionCount++
            if (questionCount < QuizStorage.firstLinesForQuestions.size) {
                quizViewModel.getBook(questionCount)
            } else {
                findNavController().navigate(R.id.action_quizFragment_to_resultsFragment)
            }
        }
    }

    private fun setChoiceButtons() {
//        binding.imgCoverBook1.setBackgroundResource(choiceList[0].image)
//        binding.imgCoverBook2.setBackgroundResource(choiceList[1].image)
//        binding.imgCoverBook3.setBackgroundResource(choiceList[2].image)
//        binding.imgCoverBook1.tooltipText = Gson().toJson(choiceList[0])
//        binding.imgCoverBook2.tooltipText = Gson().toJson(choiceList[1])
//        binding.imgCoverBook3.tooltipText = Gson().toJson(choiceList[2])
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}