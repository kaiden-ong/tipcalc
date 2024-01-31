package edu.uw.ischool.kong314.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tipPercent = .15
        val button = findViewById<Button>(R.id.button)
        val field = findViewById<EditText>(R.id.editTextNumberDecimal)
        button.setOnClickListener {
            val amount = field.text.toString().toDoubleOrNull() ?: 0.0
            buttonPressed(amount * tipPercent)
            Log.d(tag, amount.toString())
            Log.d(tag, tipPercent.toString())
        }
    }

    private fun buttonPressed(tip: Double) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, "$" + String.format("%.2f", tip), duration) // in Activity
        Log.d(tag, tip.toString())
        toast.show()
    }
}