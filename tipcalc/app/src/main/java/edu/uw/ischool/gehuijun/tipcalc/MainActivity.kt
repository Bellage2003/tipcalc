package edu.uw.ischool.gehuijun.tipcalc

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var amountEditText: EditText
    private lateinit var tipButton: Button
    private lateinit var tipSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        amountEditText = findViewById(R.id.amountEditText)
        tipButton = findViewById(R.id.tipButton)
        tipSpinner = findViewById(R.id.tipSpinner)

        tipButton.isEnabled = false

        amountEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let {
                    val userInput = it.toString()
                    val amount = userInput.substring(1).toDoubleOrNull()
                    tipButton.isEnabled = amount != null && amount > 0
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        tipButton.setOnClickListener {
            val userInput = amountEditText.text.toString()
            val amount = userInput.toDoubleOrNull()
            val tipPercentage = tipSpinner.selectedItem.toString().removeSuffix("%").toDouble()

            if (amount != null) {
                val tipAmount = amount * (tipPercentage / 100)
                val formattedTip = String.format("%.2f", tipAmount)
                val toastMessage = "Tip: $$formattedTip"
                Toast.makeText(this, toastMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
}




