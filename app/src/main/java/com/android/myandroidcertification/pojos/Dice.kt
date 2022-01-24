package com.android.myandroidcertification.pojos

class Dice(private val sides: Int) {

    fun rollDice(): Int {
        return (1..sides).random()
    }
}