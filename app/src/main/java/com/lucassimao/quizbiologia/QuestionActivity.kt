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

    private var currentQuestion = 0
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
            questionTitle.text = initListQuestions()[currentQuestion].question
            rbAlternativeA.text = initListQuestions()[currentQuestion].alternativeA
            rbAlternativeB.text = initListQuestions()[currentQuestion].alternativeB
            rbAlternativeC.text = initListQuestions()[currentQuestion].alternativeC
            rbAlternativeD.text = initListQuestions()[currentQuestion].alternativeD

            buttonIsEnable(false)

            btnNext.setOnClickListener {
                currentQuestion++
                nextQuestion(currentQuestion)
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
        if (answer == initListQuestions()[currentQuestion].correctAnswer) {
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
                "Questão 1 (Unimep - RJ) Um homem apresenta o genótipo Aa Bb CC dd e sua " +
                        "esposa, o genótipo aa Bb cc Dd. Qual é a probabilidade desse casal ter um" +
                        " filho do sexo masculino e portador dos genes bb?",
                "a) 1/4",
                "b) 1/8",
                "c) 1/2",
                "d) nenhuma das anteriores",
                2
            ),
            QuestionModel(
                "Questão 2 Sobre a recombinação gênica é correto afirmar que:",
                "a) A recombinação gênica pode ser homóloga ou heteróloga.",
                "b) A principal diferença entre os tipos de recombinação é que ocorrem em " +
                        "etapas diferentes da mitose.",
                "c) A recombinação gênica é classificada em homóloga e não-homóloga.",
                "d) Enquanto um tipo de recombinação permite a variabilidade, o outro " +
                        "tipo apresenta limitações.",
                3,

                ),
            QuestionModel(
                "Questão 3 (UERJ) Sabe-se que a transmissão hereditária da cor das flores " +
                        "conhecidas como copo-de-leite se dá por herança mendeliana simples, com " +
                        "dominância completa. Em um cruzamento experimental de copos-de-leite " +
                        "vermelhos, obteve-se uma primeira geração – F1 - bastante numerosa, numa " +
                        "proporção de 3 descendentes vermelhos para cada branco (3:1). Analisando o " +
                        "genótipo da F1, os cientistas constataram que apenas um em cada três " +
                        "descendentes vermelhos era homozigoto para essa característica. \n De acordo " +
                        "com tais dados, pode-se afirmar que a produção genotípica da F1 desse " +
                        "cruzamento experimental foi:",
                "a) 4 Aa",
                "b) 2 Aa : 2 aa",
                "c) 3 AA : 1 Aa",
                "d) 1 AA : 2 Aa : 1 aa",
                4

            ),
            QuestionModel(
                "Questão 4 (Fatec) Pares de genes, com segregação independente podem agir, " +
                        "conjuntamente, na determinação de uma mesma característica fenotípica. " +
                        "Este fenômeno é conhecido como:",
                "a) interação gênica",
                "b) epistasia",
                "c) poligenia",
                "d) dominância completa",
                4

            ),
            QuestionModel(
                "Questão 5 (Mack) A acondroplasia é um tipo de nanismo em que a cabeça e o" +
                        " tronco são normais, mas braços e pernas são muito curtos. É condicionado " +
                        "por um gene dominante que, em homozigose, provoca a morte antes do " +
                        "nascimento. Os indivíduos normais são recessivos e os afetados são " +
                        "heterozigotos. A probabilidade de um casal acondroplásico ter uma criança " +
                        "de sexo feminino normal é de:",
                "a) 1/6",
                "b) 1/8",
                "c) 2/5",
                "d) 1/2",
                1

            ),
        )
    }
}