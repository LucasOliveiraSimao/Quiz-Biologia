package com.lucassimao.quizbiologia

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.lucassimao.quizbiologia.databinding.ActivityQuestionBinding
import com.lucassimao.quizbiologia.utils.answerA
import com.lucassimao.quizbiologia.utils.answerB
import com.lucassimao.quizbiologia.utils.answerC
import com.lucassimao.quizbiologia.utils.answerD

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding

    private var indexCurrentQuestion = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)

        initViews()

        setContentView(binding.root)
    }

    private fun setupAlertDialog(score: Int) {
        val alert = AlertDialog.Builder(this).create()
        val view = layoutInflater.inflate(R.layout.custom_alert_dialog, null)

        val dialogDismiss = view.findViewById<Button>(R.id.btn_dialogDismiss)
        val dialogMessage = view.findViewById<TextView>(R.id.txt_scored)

        val message = getString(R.string.label_scored, score.minus(1), initListQuestions().size)

        dialogMessage.text = message

        alert.setView(view)

        dialogDismiss.setOnClickListener {
            finish()
        }

        alert.setCancelable(false)
        alert.show()
    }

    private fun initViews() {
        binding.apply {
            questionTitle.text = initListQuestions()[indexCurrentQuestion].question
            rbAlternativeA.text = initListQuestions()[indexCurrentQuestion].alternativeA
            rbAlternativeB.text = initListQuestions()[indexCurrentQuestion].alternativeB
            rbAlternativeC.text = initListQuestions()[indexCurrentQuestion].alternativeC
            rbAlternativeD.text = initListQuestions()[indexCurrentQuestion].alternativeD

            buttonIsEnable(false)

            btnNext.setOnClickListener {
                indexCurrentQuestion++
                nextQuestion(indexCurrentQuestion)
            }

            checkAlternatives()

        }
    }

    private fun buttonIsEnable(b: Boolean) {
        binding.btnNext.isEnabled = b
    }

    private fun checkAlternatives() {
        binding.rgAlternatives.setOnCheckedChangeListener { _, id ->
            buttonIsEnable(true)
            when (id) {
                R.id.rb_alternativeA -> {
                    checkCorrectAnswer(answerA)
                }
                R.id.rb_alternativeB -> {
                    checkCorrectAnswer(answerB)
                }
                R.id.rb_alternativeC -> {
                    checkCorrectAnswer(answerC)
                }
                R.id.rb_alternativeD -> {
                    checkCorrectAnswer(answerD)
                }
                else -> {
                    return@setOnCheckedChangeListener
                }
            }

        }
    }

    private fun checkCorrectAnswer(answer: Int) {
        if (answer == initListQuestions()[indexCurrentQuestion].correctAnswer) {
            score++
        }
    }

    private fun nextQuestion(currentQuestion: Int) {

        if (currentQuestion >= initListQuestions().size) {
            setupAlertDialog(score)
        } else {
            binding.apply {
                rgAlternatives.clearCheck()
                questionTitle.text = initListQuestions()[currentQuestion].question
                rbAlternativeA.text = initListQuestions()[currentQuestion].alternativeA
                rbAlternativeB.text = initListQuestions()[currentQuestion].alternativeB
                rbAlternativeC.text = initListQuestions()[currentQuestion].alternativeC
                rbAlternativeD.text = initListQuestions()[currentQuestion].alternativeD

                buttonIsEnable(false)

            }
        }

        checkAlternatives()

    }

    private fun initListQuestions(): List<QuestionModel> {

        return arrayListOf(
            QuestionModel(
                "Quest??o 1 (Unimep - RJ) Um homem apresenta o gen??tipo Aa Bb CC dd e sua " +
                        "esposa, o gen??tipo aa Bb cc Dd. Qual ?? a probabilidade desse casal ter um" +
                        " filho do sexo masculino e portador dos genes bb?",
                "a) 1/4",
                "b) 1/8",
                "c) 1/2",
                "d) nenhuma das anteriores",
                2
            ),
            QuestionModel(
                "Quest??o 2 Sobre a recombina????o g??nica ?? correto afirmar que:",
                "a) A recombina????o g??nica pode ser hom??loga ou heter??loga.",
                "b) A principal diferen??a entre os tipos de recombina????o ?? que ocorrem em " +
                        "etapas diferentes da mitose.",
                "c) A recombina????o g??nica ?? classificada em hom??loga e n??o-hom??loga.",
                "d) Enquanto um tipo de recombina????o permite a variabilidade, o outro " +
                        "tipo apresenta limita????es.",
                3,

                ),
            QuestionModel(
                "Quest??o 3 (UERJ) Sabe-se que a transmiss??o heredit??ria da cor das flores " +
                        "conhecidas como copo-de-leite se d?? por heran??a mendeliana simples, com " +
                        "domin??ncia completa. Em um cruzamento experimental de copos-de-leite " +
                        "vermelhos, obteve-se uma primeira gera????o ??? F1 - bastante numerosa, numa " +
                        "propor????o de 3 descendentes vermelhos para cada branco (3:1). Analisando o " +
                        "gen??tipo da F1, os cientistas constataram que apenas um em cada tr??s " +
                        "descendentes vermelhos era homozigoto para essa caracter??stica. \n De acordo " +
                        "com tais dados, pode-se afirmar que a produ????o genot??pica da F1 desse " +
                        "cruzamento experimental foi:",
                "a) 4 Aa",
                "b) 2 Aa : 2 aa",
                "c) 3 AA : 1 Aa",
                "d) 1 AA : 2 Aa : 1 aa",
                4

            ),
            QuestionModel(
                "Quest??o 4 (Fatec) Pares de genes, com segrega????o independente podem agir, " +
                        "conjuntamente, na determina????o de uma mesma caracter??stica fenot??pica. " +
                        "Este fen??meno ?? conhecido como:",
                "a) intera????o g??nica",
                "b) epistasia",
                "c) poligenia",
                "d) domin??ncia completa",
                4

            ),
            QuestionModel(
                "Quest??o 5 (Mack) A acondroplasia ?? um tipo de nanismo em que a cabe??a e o" +
                        " tronco s??o normais, mas bra??os e pernas s??o muito curtos. ?? condicionado " +
                        "por um gene dominante que, em homozigose, provoca a morte antes do " +
                        "nascimento. Os indiv??duos normais s??o recessivos e os afetados s??o " +
                        "heterozigotos. A probabilidade de um casal acondropl??sico ter uma crian??a " +
                        "de sexo feminino normal ?? de:",
                "a) 1/6",
                "b) 1/8",
                "c) 2/5",
                "d) 1/2",
                1

            ),
        )
    }
}