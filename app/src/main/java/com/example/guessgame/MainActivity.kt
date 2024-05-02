package com.example.guessgame

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.guessgame.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var mLeftBtn: Button
    private lateinit var mRightBtn: Button
    private lateinit var mPointsLbl: TextView
    private var mPoints: Int = 0
    private lateinit var mConstraintLayout: ConstraintLayout
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        mLeftBtn = findViewById(/* id = */ R.id.mLtButton)
        mRightBtn = findViewById(/* id = */ R.id.mRtButton)
        mPointsLbl = findViewById(/* id = */ R.id.mPointsView)
        mConstraintLayout = findViewById(R.id.main)
        setRandomNumbersOnButtons()

        binding.mRtButton.setOnClickListener {
            val lSelGreatVal = mRightBtn.text.toString().toInt()
            val lSelLessVal = mLeftBtn.text.toString().toInt()
            handleResult(lSelGreatVal, lSelLessVal)
        }
    }
    public fun callThisOnLeftClick(aView: View)
    {
        val lSelGreatVal = mLeftBtn.text.toString().toInt()
        val lSelLessVal = mRightBtn.text.toString().toInt()
        handleResult(lSelGreatVal, lSelLessVal)
    }

    private fun setRandomNumbersOnButtons() {
        val lRnadInt = Random.nextInt(100)
        var lRnadInt2 = Random.nextInt(100)
        while (lRnadInt == lRnadInt2) {
            lRnadInt2 = Random.nextInt(100)
        }
        mLeftBtn.text = "$lRnadInt"
        mRightBtn.text = "$lRnadInt2"
    }

    private fun isSelecedGreater(aSelected: Int, aUnselected: Int): Boolean {
        return aSelected > aUnselected
    }

    private fun increasePointsAndShow() {
        mPoints += 5
        mPointsLbl.text = "Points = $mPoints"
        mConstraintLayout.setBackgroundColor(Color.GREEN)
    }

    private fun decreasePointsAndShow() {
        mPoints -= 5
        mPointsLbl.text = "Points = $mPoints"
        mConstraintLayout.setBackgroundColor(Color.RED)
    }

    private fun handleResult(aSelected: Int, aUnselected: Int)
    {
        if (isSelecedGreater(aSelected, aUnselected)) {
            increasePointsAndShow()
        } else {
            decreasePointsAndShow()
        }
        setRandomNumbersOnButtons()
        checkScore()
    }

    private fun checkScore()
    {
        if (mPoints <= -15)
        {
            Toast.makeText(this, "Game Over, You suck at this:(", Toast.LENGTH_LONG).show()
            resetGame()
        }
        if (mPoints > 30)
        {
            Toast.makeText(this, "You win!", Toast.LENGTH_LONG).show()
            resetGame()
        }
    }

    private fun resetGame()
    {
        mPoints = 0
        mPointsLbl.text = "Points = $mPoints"
        mConstraintLayout.setBackgroundColor(Color.WHITE)
    }
}