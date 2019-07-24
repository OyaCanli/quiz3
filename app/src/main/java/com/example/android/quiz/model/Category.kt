package com.example.android.quiz.model


import android.support.annotation.ColorRes
import android.support.annotation.StringRes
import com.example.android.quiz.R


enum class Category(@StringRes val categoryName: Int,
                    @ColorRes val categoryColor: Int) {

    LITERATURE(R.string.literature, R.color.literature),

    CINEMA(R.string.cinema, R.color.cinema),

    SCIENCE(R.string.science, R.color.science)
}