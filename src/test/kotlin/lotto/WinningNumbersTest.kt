package lotto

import lotto.model.Const
import lotto.model.Numbers
import lotto.model.WinningTicket
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `winning numbers in range 1 to 45`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(Numbers(listOf(10, 2, 3, 4, 5, 6)))
            require(ticket.winningNumbers.getNumbers().all { it in Const.MIN_RANGE..Const.MAX_RANGE })
        }
    }

    @Test
    fun `winning numbers not in range 1 to 45`() {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(Numbers(listOf(10, 2, 3, 54, 5, 6)))
            require(ticket.winningNumbers.getNumbers().all { it in Const.MIN_RANGE..Const.MAX_RANGE })
        }
    }

    @Test
    fun `winning numbers are unique`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(Numbers(listOf(10, 2, 34, 45, 5, 13)))
            require(ticket.winningNumbers.getNumbers().toSet().size == Const.NUMBER_COUNT)
        }
    }

    @Test
    fun `winning numbers are not unique`() {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(Numbers(listOf(10, 2, 34, 5, 2, 13)))
            require(ticket.winningNumbers.getNumbers().toSet().size == Const.NUMBER_COUNT)
        }
    }

    @Test
    fun `size winning numbers are not 6`() {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(Numbers(listOf(10, 2, 34, 5, 2)))
            require(ticket.winningNumbers.getNumbers().size == Const.NUMBER_COUNT)
        }
    }

    @Test
    fun `size winning numbers are  6`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(Numbers(listOf(10, 2, 34, 45, 5, 13)))
            require(ticket.winningNumbers.getNumbers().size == Const.NUMBER_COUNT)
        }
    }

    @Test
    fun `bonus number is in the range 1 to 45`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(Numbers(listOf(1, 2, 34, 45, 5, 13)), 11)
            require(ticket.bonusNumber in Const.MIN_RANGE..Const.MAX_RANGE)
        }
    }

    @Test
    fun `bonus number is not in the range 1 to 45`() {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(Numbers(listOf(10, 2, 34, 45, 5, 13)), 100)
            require(ticket.bonusNumber in Const.MIN_RANGE..Const.MAX_RANGE)
        }
    }

    @Test
    fun `bonus number not in the list of winning numbers`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(Numbers(listOf(1, 2, 3, 4, 5, 6)), 11)
            require(!ticket.winningNumbers.contains(ticket.bonusNumber))
        }
    }

    @Test
    fun `bonus number in the list of winning numbers`() {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(Numbers(listOf(1, 2, 3, 4, 5, 6)), 2)
            require(ticket.winningNumbers.contains(ticket.bonusNumber))
        }
    }
}
