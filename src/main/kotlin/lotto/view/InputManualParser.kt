package lotto.view

import lotto.model.Const
import lotto.model.Numbers

object InputManualParser {
    fun parseManualTicketCount(
        input: String,
        maxTickets: Int,
    ): Int {
        val count = input.trim().toIntOrNull() ?: throw IllegalArgumentException(Const.MAN_TICKET_NUM)
        require(count in 0..maxTickets) { "Manual ticket count must be between 0 and $maxTickets" }
        return count
    }

    fun parseManualTicketNumbers(input: String): Numbers {
        val numbers =
            input.split(",").map {
                it.trim().toIntOrNull() ?: throw IllegalArgumentException("Ticket numbers must be numeric.")
            }
        return Numbers(numbers)
    }
}
