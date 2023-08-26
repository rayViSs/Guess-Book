package com.example.guessbook.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guessbook.R
import com.example.guessbook.databinding.FragmentResultsBinding
import com.example.guessbook.presentation.recyclerAdapter.RightAnswersAdapter
import com.example.guessbook.presentation.viewmodels.ResultsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultsFragment : Fragment() {

    private var _binding: FragmentResultsBinding? = null
    private val binding get() = _binding!!

    private val resultsViewModel: ResultsViewModel by viewModels()
    private lateinit var resultsAdapter: RightAnswersAdapter

    companion object {
        fun newInstance() = ResultsFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResultsBinding.inflate(layoutInflater)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultsViewModel.getRigthAndSavedAnswers()

        setObservers()
        initAdapter()
        applyRecycler()
        applyButtonClick()

    }

    private fun setObservers() {
        resultsViewModel.rightAnswersCount.observe(viewLifecycleOwner) {
            binding.textViewResult.text = getString(R.string.count_right_answers_textview, it)
            binding.countRightAnswersTextView.text = getString(R.string.count_right_answers_progress, it, 10)
            binding.resultsProgressBar.progress = it
        }

        resultsViewModel.rightAnswers.observe(viewLifecycleOwner) { rightAnswers ->
            resultsViewModel.userAnswers.observe(viewLifecycleOwner) { userAnswers ->
                resultsAdapter.updateLists(rightAnswers, userAnswers)
            }
        }
    }

    private fun initAdapter() {
        resultsAdapter = RightAnswersAdapter(requireContext(), listOf(), listOf())
    }

    private fun applyRecycler() {
        with(binding.recyclerViewAnswers) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = resultsAdapter
        }
    }

    private fun applyButtonClick() {
        binding.buttonCheckCorrectAnswers.setOnClickListener {
            binding.resultsContainer.visibility = View.VISIBLE
        }

        binding.restartBtn.setOnClickListener {
            resultsViewModel.clearAnswers()
            findNavController().navigate(R.id.action_resultsFragment_to_quizFragment)
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}