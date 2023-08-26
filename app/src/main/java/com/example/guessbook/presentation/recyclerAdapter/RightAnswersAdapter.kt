package com.example.guessbook.presentation.recyclerAdapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.guessbook.data.quiz.FirstLine
import com.example.guessbook.databinding.RightAnswerCardBinding
import com.example.guessbook.R

class RightAnswersAdapter(
    private val context: Context,
    private var rightAnswers: List<FirstLine>,
    private var userAnswers: List<FirstLine>
) : RecyclerView.Adapter<RightAnswersAdapter.RightAnswerHolder>() {

    class RightAnswerHolder(val binding: RightAnswerCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RightAnswerHolder =
        RightAnswerHolder(RightAnswerCardBinding.inflate(LayoutInflater.from(context), parent, false))

    override fun getItemCount(): Int = rightAnswers.size

    override fun onBindViewHolder(holder: RightAnswerHolder, position: Int) {
        val rightAnswer = rightAnswers[position]
        val userAnswer = userAnswers[position]
        with(holder.binding) {
            firstLineTextView.text =
                rightAnswer.book.firstLine
            imgUserCoverBook.setImageResource(userAnswer.book.bookCover)
            imgRightCoverBook.setImageResource(rightAnswer.book.bookCover)
            if(rightAnswer == userAnswer){
                imageStatusResult.setImageResource(R.drawable.ic_check)
            } else{
                imageStatusResult.setImageResource(R.drawable.ic_cross)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateLists(correctAnswersList: List<FirstLine>, savedAnswersList: List<FirstLine>) {
        rightAnswers = correctAnswersList
        userAnswers = savedAnswersList
        notifyDataSetChanged()
    }
}