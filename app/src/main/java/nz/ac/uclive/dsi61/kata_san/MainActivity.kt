package nz.ac.uclive.dsi61.kata_san

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

const val REQUESTCODE_QUIZ = 100

class MainActivity : Activity() {

    private lateinit var scoreText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        scoreText = findViewById(R.id.scoreText)
        val katakanaButton: Button = findViewById(R.id.katakanaButton)
        val hiraganaButton: Button = findViewById(R.id.hiraganaButton)

        katakanaButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("alphabet", Japanese.katakana)
            startActivityForResult(intent, REQUESTCODE_QUIZ)
        }

        hiraganaButton.setOnClickListener {
            val intent = Intent(this, QuizActivity::class.java)
            intent.putExtra("alphabet", Japanese.hiragana)
            startActivityForResult(intent, REQUESTCODE_QUIZ)
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUESTCODE_QUIZ && resultCode == Activity.RESULT_OK) {
            scoreText.text = "Last score: ${data?.getIntExtra("score", 0)}"
        }

    }


}

