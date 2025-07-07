package lotto

import lotto.view.InputManualParser
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class InputManualParserTest {
    @Test
    fun `parseManualTicketCount parses valid input`() {
        val result = InputManualParser.parseManualTicketCount("3", 5)
        assertEquals(3, result)
    }

    @Test
    fun `parseManualTicketCount throws on non-numeric input`() {
        assertThrows<IllegalArgumentException> {
            InputManualParser.parseManualTicketCount("three", 5)
        }
    }

    @Test
    fun `parseManualTicketCount throws when out of range`() {
        assertThrows<IllegalArgumentException> {
            InputManualParser.parseManualTicketCount("6", 5)
        }
    }

    @Test
    fun `parseManualTicketNumbers parses valid string into Numbers`() {
        val result = InputManualParser.parseManualTicketNumbers("8, 21, 23, 41, 42, 43")
        assertTrue(result.contains(8))
        assertTrue(result.contains(43))
    }

    @Test
    fun `parseManualTicketNumbers throws on non-numeric value`() {
        assertThrows<IllegalArgumentException> {
            InputManualParser.parseManualTicketNumbers("8, 21, dfc, 41, 42, 43")
        }
    }

    @Test
    fun `parseManualTicketNumbers throws duplicates`() {
        assertThrows<IllegalArgumentException> {
            InputManualParser.parseManualTicketNumbers("1, 2, 3, 3, 4, 5")
        }
    }

    @Test
    fun `parseManualTicketNumbers must be between 1 and 45`() {
        assertThrows<IllegalArgumentException> {
            InputManualParser.parseManualTicketNumbers("8, 21, 80, 41, 42, 43")
        }
    }

    @Test
    fun `size ticket numbers is not 6`() {
        assertThrows<IllegalArgumentException> {
            InputManualParser.parseManualTicketNumbers("8, 21, 80, 41, 42")
        }
    }
}
