package com.example.hw1_guessinggame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var numberRandom: Int? = null
    private var attempts: Int = 0
    private var isFinish: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val range = (1..1000)
        numberRandom = range.random()

        playAgainButton.setOnClickListener {
            if (attempts == 0) {
                helpText.text = ""
                playAgainButton.text = getString(R.string.play_again)
            }
            val inputUser = inputNumberEditText.text.toString()
            if (inputUser.isNotBlank()) {
                attempts += 1
                if (inputUser.toInt() in range) {
                    when (inputUser.toInt()) {
                        numberRandom!! -> {
                            helpText.text = getString(R.string.winner) + " $attempts"
                            isFinish = true
                        }
                        in (1..numberRandom!!) -> helpText.text = getString(R.string.higher)
                        in (numberRandom!!..1000) -> helpText.text = getString(R.string.lower)
                    }
                    if (isFinish) {
                        playAgainButton.text = getString(R.string.restart_game)
                        numberRandom = range.random()
                        isFinish = false
                        attempts = 0
                        inputNumberEditText.setText("")
                    }
                } else
                    helpText.text = getString(R.string.out_of_range)
            }
        }
    }
}
