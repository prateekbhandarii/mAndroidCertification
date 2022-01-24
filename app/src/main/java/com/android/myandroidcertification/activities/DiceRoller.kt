package com.android.myandroidcertification.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.myandroidcertification.R
import com.android.myandroidcertification.databinding.ActivityRollingDiceBinding
import com.android.myandroidcertification.pojos.Dice

class DiceRoller : AppCompatActivity() {

    private lateinit var binding: ActivityRollingDiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRollingDiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imageView.visibility = View.GONE

        binding.button.setOnClickListener {
            when (Dice(6).rollDice()) {
                1 -> binding.imageView.setImageResource(R.drawable.dice_1)
                2 -> binding.imageView.setImageResource(R.drawable.dice_2)
                3 -> binding.imageView.setImageResource(R.drawable.dice_3)
                4 -> binding.imageView.setImageResource(R.drawable.dice_4)
                5 -> binding.imageView.setImageResource(R.drawable.dice_5)
                6 -> binding.imageView.setImageResource(R.drawable.dice_6)
            }
            binding.imageView.visibility = View.VISIBLE
        }
    }
}