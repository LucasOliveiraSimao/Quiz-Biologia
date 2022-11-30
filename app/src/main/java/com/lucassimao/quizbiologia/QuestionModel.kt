package com.lucassimao.quizbiologia

data class QuestionModel(
    var question: String,
    var alternativeA: String,
    var alternativeB: String,
    var alternativeC: String,
    var alternativeD: String,
    var correctAnswer: Int
)