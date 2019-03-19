package com.danilodequeiroz.lodjinha

import com.danilodequeiroz.lodjinha.common.util.toBRLMoneyString
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MoneyConversionUnitTest {


    @Test
    fun toBRLMoneyString_Double_String() {
        val normalPrice = 119
        assertEquals(normalPrice.toBRLMoneyString(), "R$ 119,00")


        val nearZero = 119.0000000000000000000001
        assertEquals(nearZero.toBRLMoneyString(), "R$ 119,00")

        val price = 119.9899999999999948840923025272786617279052734375
        assertEquals(price.toBRLMoneyString(), "R$ 119,99")
    }

    @Test
    fun toBRLMoneyString_Int_String() {
        val price = 119
        assertEquals(price.toBRLMoneyString(), "R$ 119,00")
    }


    @Test
    fun toBRLMoneyString_Float_String() {
        val price = 119f
        assertEquals(price.toBRLMoneyString(), "R$ 119,00")


        val floatPrice= 119.9899999999999948840923025272786617279052734375f
        assertEquals(floatPrice.toBRLMoneyString(), "R$ 119,99")
    }
}
