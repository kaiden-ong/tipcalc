package edu.uw.ischool.kong314.tipcalc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val tag = "FromMain"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val tipPercent = .15
        val button = findViewById<Button>(R.id.button)
        button.setBackgroundColor(Color.argb(255, 220, 220, 220))
        val field = findViewById<EditText>(R.id.editTextNumberDecimal)
        button.setOnClickListener {
            val amount = field.text.toString().toDoubleOrNull() ?: 0.0
            buttonPressed(amount * tipPercent)
            Log.d(tag, amount.toString())
            Log.d(tag, tipPercent.toString())
        }

        field.addTextChangedListener(object: TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d(tag, "Before")
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                Log.d(tag, "Changed")
                if (p0.toString() == "") {
                    button.isEnabled = false
                    button.setBackgroundColor(Color.argb(255, 220, 220, 220))
                } else {
                    button.isEnabled = true
                    button.setBackgroundColor(Color.argb(255, 238, 130, 238))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d(tag, "After")
                val temp = p0
                if (p0.toString() == "$") {
                    field.setText("")
                    Selection.setSelection(field.text, field.text.length)
                } else if (!p0.toString().startsWith("$")) {
                    field.setText("$" + temp)
                    Selection.setSelection(field.text, field.text.length)
                }
            }

        })
    }

    private fun buttonPressed(tip: Double) {
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(this, "$" + String.format("%.2f", tip), duration) // in Activity
        Log.d(tag, tip.toString())
        toast.show()
    }
}