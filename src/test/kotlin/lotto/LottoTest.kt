package lotto

import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows

class LottoTest {
//
//    @Test
//    fun `Amount creates a Lotto instance`() {
//        val lotto = Lotto(2000)
//        assertNotNull(lotto)
//    }

    @Test
    fun `Amount is greater than 1000`() {
        assertThrows<IllegalArgumentException> {
            val lotto = Lotto(2000)
            requireNotNull(lotto.amount >= 1000) {"Amount should be greater than or equal to 1000"}
        }
    }

    @Test
    fun `Amount is divisible by 1000`() {
        assertThrows<IllegalArgumentException> {
            val lotto = Lotto(2000)
            require(lotto.amount / 1000 == 0) {"Amount should be divisible by 1000"}
        }
    }
}