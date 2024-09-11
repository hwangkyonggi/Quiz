package com.example.quiz

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.appcompat.app.AppCompatActivity
import com.example.quiz.databinding.ActivityMainBinding
const val QUIZ_COUNT = 5

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var rightAnswer: String? = null
    private var rightAnswerCount = 0
    private var quizCount = 1

    private val quizData = mutableListOf(
        listOf("膃肭臍", "おっとせい"),
        listOf("馴鹿", "となかい"),
        listOf("水豚", "かぴばら"),
        listOf("饂飩", "うどん"),
        listOf("竜髭菜", "あすぱらがす"),
        listOf("馬穴", "バケツ"),
        listOf("杓文字", "しゃもじ")
    )



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputAnswer.setOnKeyListener { _, keyCode, keyEvent ->
            if (keyEvent.action == KeyEvent.ACTION_DOWN
                && keyCode == KeyEvent.KEYCODE_ENTER) {
                checkAnswer()
                true
            } else {
                false
            }
        }

        quizData.shuffle()
        showNextQuiz()

        Log.d("abcd", quizData.toString())
    }


    private fun showNextQuiz() {
        binding.countLabel.text = getString(R.string.count_label, quizCount)

        val quiz = quizData[0]
        binding.questionLabel.text = quiz[0]
        rightAnswer = quiz[1]

        quizData.removeAt(0)
    }

    private fun checkAnswer() {
        val answerText = binding.inputAnswer.text.toString()

        val alertTitle: String
        if (answerText == rightAnswer) {
            alertTitle = "正解！"
            rightAnswerCount++
        } else {
            alertTitle = "不正解..."
        }

        val answerDialogFragment = AnswerDialogFragment()

        val bundle = Bundle().apply {
            putString("TITLE", alertTitle)
            putString("MESSAGE", "答え：${rightAnswer}")
        }
        answerDialogFragment.arguments = bundle

        answerDialogFragment.isCancelable = false

        answerDialogFragment.show(supportFragmentManager, "my_dialog")
    }


    fun checkQuizCount () {
        if (quizCount == QUIZ_COUNT){
            val intent = Intent(this@MainActivity, ResultActivity::class.java)
            intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
            startActivity(intent)
        }else{
            binding.inputAnswer.text.clear()
            quizCount++
            showNextQuiz()
        }

    }
}