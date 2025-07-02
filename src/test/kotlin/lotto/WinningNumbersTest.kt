package lotto

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningNumbersTest {
    @Test
    fun `winning numbers in range 1 to 45`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(listOf(1,2,3,4,5,6))
            require(ticket.winningNumbers.all { it in 1..45})
        }
    }

    @Test
    fun `winning numbers not in range 1 to 45`() {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(listOf(1,2,3,54,5,6))
            require(ticket.winningNumbers.all { it in 1..45})
        }
    }

    @Test
    fun `winning numbers are unique`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(listOf(1,2,34,45,5,13))
            require(ticket.winningNumbers.toSet().size == 6)
        }
    }

    @Test
    fun `winning numbers are not unique`() {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(listOf(1,2,34,5,2,13))
            require(ticket.winningNumbers.toSet().size == 6)
        }
    }

    @Test
    fun `size winning numbers are not 6` () {
        assertThrows<IllegalArgumentException> {
            val ticket = WinningTicket(listOf(1,2,34,5,2))
            require(ticket.winningNumbers.size == 6)

        }
    }

    @Test
    fun `size winning numbers are  6`() {
        assertDoesNotThrow {
            val ticket = WinningTicket(listOf(1,2,34,45,5,13))
            require(ticket.winningNumbers.size == 6)
        }
    }

    @Test

}