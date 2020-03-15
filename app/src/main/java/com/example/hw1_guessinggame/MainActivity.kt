package com.example.hw1_guessinggame

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var numberRandom: Int? = null
    private var attempts: Int = 0
    private var isFinish: Boolean = false
    private val range = (1..1000)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numberRandom = range.random()

        playAgainButton.setOnClickListener {
            //Si acabo de iniciar el juego
            if (attempts == 0) {
                helpText.text = ""
                playAgainButton.text = getString(R.string.play_again)
            }
            //obtiene entrada del usuario
            val inputUser = inputNumberEditText.text.toString()
            if (inputUser.isNotBlank()) {
                attempts += 1
                if (inputUser.toInt() in range) {
                    //evalua valor ingresado
                    when (inputUser.toInt()) {
                        numberRandom!! -> {
                            helpText.text = getString(R.string.winner) + " $attempts"
                            isFinish = true
                        }
                        in (1..numberRandom!!) -> helpText.text = getString(R.string.higher)
                        in (numberRandom!!..1000) -> helpText.text = getString(R.string.lower)
                    }
                    if (isFinish) resetGame()
                } else helpText.text = getString(R.string.out_of_range)
            }
        }
    }

    /**
     * reinicia el juego
     */
    private fun resetGame() {
        playAgainButton.text = getString(R.string.restart_game)
        numberRandom = range.random()
        isFinish = false
        attempts = 0
        inputNumberEditText.setText("")
    }
}
