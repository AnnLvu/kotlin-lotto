package lotto
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class WinningRankTest {
    @Test
    fun `6 numbers match`() {
        val ticket = Ticket(listOf(1,2,3,4,5,6))
        val winningNumbers = WinningTicket(listOf(1,2,3,4,5,6))

    }

}