package com.android.myandroidcertification

import com.android.myandroidcertification.pojos.Dice
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun generate_numbers() {
        val dice = Dice(6)
        val result = dice.rollDice()
        assertTrue(result in 1..6)
    }
}