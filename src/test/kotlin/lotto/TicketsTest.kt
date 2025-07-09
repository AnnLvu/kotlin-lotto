package lotto

import lotto.model.Numbers
import lotto.model.Rank
import lotto.model.Tickets
import lotto.model.WinningTicket
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TicketsTest {
    @Test
    fun `generate creates two tickets with one manual`() {
        val amount = 2000
        val manualTickets = listOf(Numbers(listOf(1, 2, 3, 4, 5, 6)))
        val tickets = Tickets.generate(amount, manualTickets)
        val ticketList = tickets.getTickets()
        assertEquals(2, ticketList.size)
        assertEquals(listOf(1, 2, 3, 4, 5, 6), ticketList[0].numbers.getNumbers())
    }

    @Test
    fun `calculateStats counts SECOND rank correctly`() {
        val manualTickets = listOf(Numbers(listOf(1, 2, 3, 4, 5, 6)))
        val tickets = Tickets.generate(1000, manualTickets)
        val winningTicket = WinningTicket(Numbers(listOf(1, 2, 3, 4, 5, 8)), 6)
        val stats = tickets.calculateStats(winningTicket)
        assertEquals(1, stats[Rank.SECOND])
        assertEquals(0, stats[Rank.FIRST])
        assertEquals(0, stats[Rank.THIRD])
        assertEquals(0, stats[Rank.FOURTH])
        assertEquals(0, stats[Rank.FIFTH])
        assertEquals(0, stats[Rank.MISS])
    }

    @Test
    fun `calculateStats counts THIRD rank correctly`() {
        val manualTickets = listOf(Numbers(listOf(1, 2, 3, 4, 5, 7)))
        val tickets = Tickets.generate(1000, manualTickets)
        val winningTicket = WinningTicket(Numbers(listOf(1, 2, 3, 4, 5, 6)), 8)
        val stats = tickets.calculateStats(winningTicket)
        assertEquals(0, stats[Rank.SECOND])
        assertEquals(0, stats[Rank.FIRST])
        assertEquals(1, stats[Rank.THIRD])
        assertEquals(0, stats[Rank.FOURTH])
        assertEquals(0, stats[Rank.FIFTH])
        assertEquals(0, stats[Rank.MISS])
    }

    @Test
    fun `calculateReturnRate returns correct rate for SECOND win`() {
        val amount = 2000
        val manualTickets = listOf(Numbers(listOf(1, 2, 3, 4, 5, 6)))
        val tickets = Tickets.generate(amount, manualTickets)
        val stats =
            mapOf(
                Rank.FIRST to 0,
                Rank.SECOND to 1,
                Rank.THIRD to 0,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0,
                Rank.MISS to 1,
            )
        val returnRate = tickets.calculateReturnRate(amount, stats)
        assertEquals("15000.00", returnRate)
    }

    @Test
    fun `calculateReturnRate returns zero for no wins`() {
        val amount = 1000
        val manualTickets = listOf(Numbers(listOf(1, 2, 3, 4, 5, 6)))
        val tickets = Tickets.generate(amount, manualTickets)
        val stats =
            mapOf(
                Rank.FIRST to 0,
                Rank.SECOND to 0,
                Rank.THIRD to 0,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0,
                Rank.MISS to 1,
            )
        val returnRate = tickets.calculateReturnRate(amount, stats)
        assertEquals("0.00", returnRate)
    }

    @Test
    fun `getTickets returns one manual ticket`() {
        val manualTickets = listOf(Numbers(listOf(1, 2, 3, 4, 5, 6)))
        val tickets = Tickets.generate(1000, manualTickets)
        val ticketList = tickets.getTickets()
        assertEquals(1, ticketList.size)
        assertEquals(listOf(1, 2, 3, 4, 5, 6), ticketList[0].numbers.getNumbers())
    }
}
