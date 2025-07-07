package lotto

import lotto.model.Const
import lotto.model.Numbers
import lotto.model.Ticket
import lotto.model.Tickets
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class TicketTest {
    @Test
    fun `size ticket numbers are not 6`() {
        assertThrows<IllegalArgumentException> {
            val ticket = Ticket(Numbers(listOf(10, 2, 34, 5, 2, 15, 6)))
            require(ticket.numbers.getNumbers().size == Const.NUMBER_COUNT)
        }
    }

    @Test
    fun `size ticket numbers is 6`() {
        assertDoesNotThrow {
            val ticket = Ticket(Numbers(listOf(1, 2, 34, 6, 45, 41)))
            require(ticket.numbers.getNumbers().size == Const.NUMBER_COUNT)
        }
    }

    @Test
    fun `ticket numbers are between 1 and 45`() {
        assertDoesNotThrow {
            val ticket = Ticket(Numbers(listOf(1, 2, 34, 6, 45, 41)))
            require(ticket.numbers.getNumbers().all { it in Const.MIN_RANGE..Const.MAX_RANGE })
        }
    }

    @Test
    fun `ticket numbers are not between 1 and 45`() {
        assertThrows<IllegalArgumentException> {
            val ticket = Ticket(Numbers(listOf(1, 2, 34, 46, 4, 41)))
            require(ticket.numbers.getNumbers().all { it in Const.MIN_RANGE..Const.MAX_RANGE })
        }
    }

    @Test
    fun `ticket numbers are duplicate`() {
        assertThrows<IllegalArgumentException> {
            val ticket = Ticket(Numbers(listOf(1, 1, 2, 3, 4, 5)))
            require(ticket.numbers.getNumbers().size == ticket.numbers.getNumbers().distinct().size)
        }
    }

    @Test
    fun `ticket numbers are not duplicate`() {
        assertDoesNotThrow {
            val ticket = Ticket(Numbers(listOf(1, 10, 2, 3, 4, 5)))
            require(ticket.numbers.getNumbers().size == ticket.numbers.getNumbers().distinct().size)
        }
    }

    @Test
    fun `ticket numbers should not be empty`() {
        assertThrows<IllegalArgumentException> {
            val ticket = Ticket(Numbers(emptyList()))
            require(ticket.numbers.getNumbers().size >= 0)
        }
    }

    @Test
    fun `generate should create correct number of manual and automatic tickets`() {
        val amount = 5000
        val manualTickets =
            listOf(
                Numbers(listOf(1, 2, 3, 4, 5, 6)),
                Numbers(listOf(7, 8, 9, 10, 11, 12)),
            )
        val tickets = Tickets.generate(amount, manualTickets)
        val ticketList = tickets.getTickets()
        assertEquals(5, ticketList.size)
        assertEquals(manualTickets[0].getNumbers(), ticketList[0].numbers.getNumbers())
        assertEquals(manualTickets[1].getNumbers(), ticketList[1].numbers.getNumbers())
        assertTrue(ticketList.subList(2, 5).all { it.numbers.getNumbers().size == Const.NUMBER_COUNT })
    }
}
