package nz.ac.uclive.dsi61.kata_san

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.random.Random

class QuizActivity : Activity() {

    private lateinit var kanaText: TextView
    private lateinit var romajiBox: EditText
    private lateinit var alphabet: Array<String>
    private var currIndex: Int = 0
    private var round: Int = 0
    private var score: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        kanaText = findViewById(R.id.kanaText)
        romajiBox = findViewById(R.id.romajiBox)

        alphabet = intent.getStringArrayExtra("alphabet")!!
        round = 0
        nextRound()

        romajiBox.setOnEditorActionListener {_, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                // You will always be correct, if you enter "abc" (for testing). Remove the second
                // half of the if condition below to not allow that.
                if ((romajiBox.text.toString() == Japanese.romaji[currIndex]) ||
                    (romajiBox.text.toString() == "abc")) {

                    Toast.makeText(this, "Correct! :D", Toast.LENGTH_SHORT).show()
                    score += 10
                    nextRound()
                } else {
                    Toast.makeText(this, "Incorrect! D:", Toast.LENGTH_SHORT).show()
                    score -= 5
                }
                true
            }
            false
        }
    }

    fun nextRound() {
        if (round < 5) {
            currIndex = Random.nextInt(alphabet.size)
            kanaText.text = alphabet[currIndex]

            romajiBox.setText("")
            romajiBox.requestFocus()

            round++
        } else {
            val result = Intent()
            result.putExtra("score", score)
            setResult(RESULT_OK, result)
            finish()
        }


    }


}