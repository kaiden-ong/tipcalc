package edu.uw.ischool.kong314.tipcalc

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Selection
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val tag = "FromMain"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tipPercentArr = arrayOf<String?>("10%", "15%", "18%", "20%")
        val spinner = findViewById<Spinner>(R.id.tipspinner)
        val ad: ArrayAdapter<*> = ArrayAdapter<Any?>(
            this,
            android.R.layout.simple_spinner_item,
            tipPercentArr)
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = ad
        val button = findViewById<Button>(R.id.button)
        button.setBackgroundColor(Color.argb(255, 220, 220, 220))
        val field = findViewById<EditText>(R.id.editTextNumberDecimal)

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
                val temp = p0?.toString()
                if (temp.isNullOrEmpty()) return
                val newValue = if (!temp.startsWith("$")) {
                    "$$temp"
                } else if (temp == "$") {
                    ""
                } else {
                    val decimalPattern = "^\\$?\\d*\\.?\\d{0,2}"
                    val regex = Regex(decimalPattern)
                    val matchResult = regex.find(temp)
                    matchResult?.value ?: temp
                }
                if (newValue != temp) {
                    field.setText(newValue)
                    field.setSelection(field.text.length)
                }
            }

            override fun afterTextChanged(p0: Editable?) {
                Log.d(tag, "After")
            }

        })

        button.setOnClickListener {
            val tipPercent = when (spinner.selectedItemPosition) {
                0 -> 0.10
                1 -> 0.15
                2 -> 0.18
                3 -> 0.20
                else -> 0.0
            }
            val amount = field.text.toString().substring(1, field.text.length).toDoubleOrNull() ?: 0.0
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