package com.android.myandroidcertification.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.myandroidcertification.R
import com.android.myandroidcertification.databinding.ActivityTipCalculatorBinding
import java.text.NumberFormat

class TipCalculator : AppCompatActivity() {

    private lateinit var binding: ActivityTipCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipResult.text = ""

        binding.btnCalculate.setOnClickListener { calculateTip() }
    }

    private fun calculateTip() {
        val serviceCost = binding.txtService.text.toString().toDoubleOrNull()

        if (serviceCost == null) {
            binding.tipResult.text = ""
            return
        }

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.radioAmazing -> 0.20
            R.id.radioSatisfied -> 0.15
            R.id.radioGood -> 0.10
            else -> 0.10
        }

        var tip = serviceCost.times(tipPercentage)

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        binding.tipResult.text =
            getString(R.string.textTipAmount, NumberFormat.getCurrencyInstance().format(tip))
    }
}