package com.example.tipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tipcalculator.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.calculateButton.setOnClickListener{ calculateTip() }
    }
    private fun calculateTip() {

        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()             //converting string input to decimal number

        if (cost == null) {                 //to return the control and not let program crash
            binding.tipResult.text = ""        //to reset the textview to display null tip
            return
        }
        //much easier to use when statement than if else
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tip = tipPercentage * cost
        //checking if round up option if enabled
        val roundUp = binding.roundUpSwitch.isChecked
        if (roundUp) {                      //if true then the code proceeds to roundup
            tip = kotlin.math.ceil(tip)     //upper round up
        }
       // NumberFormat.getCurrencyInstance()    = formatting the tip
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)   //storing formatted tip
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)

    }
}