package lotto

import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class TicketTest {
    @Test
    fun `ticket numbers are between 1 to 45`(){
        assertDoesNotThrow {
            val ticket = Ticket(listOf(1,2,34,6,45,41))
        }
    }

    @Test
    fun `ticket numbers are not between 1 to 45`(){
        assertThrows<IllegalArgumentException> {
            val ticket = Ticket(listOf(1,2,34,46,4,41))
        }
    }

    @Test
    fun `ticket numbers are between 1 to 45, 3`(){ // fixme: numbers should unique
        assertThrows<IllegalArgumentException> {
            val ticket = Ticket(listOf(1,1,2,3,4,5))
        }
    }

}