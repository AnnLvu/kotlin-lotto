package lotto.model

class TicketGenerator(
    private val amount: Int,
    private val manualTicketsNumbers: List<Numbers> = emptyList(),
) {
    fun generateTickets(): List<Ticket> {
        val totalTicketCount = amount / Const.PRICE
        val manualTicketCount = manualTicketsNumbers.size
        val autoTicketCount = totalTicketCount - manualTicketCount
        val manualTicketList = manualTicketsNumbers.map { Ticket(it) }
        val autoTicketList = generateAutoTickets(autoTicketCount)
        return manualTicketList + autoTicketList
    }

    private fun generateAutoTickets(count: Int): List<Ticket> = List(count) { Ticket(Numbers(generateTicketNumbers())) }

    private fun generateTicketNumbers(): List<Int> {
        return (Const.MIN_RANGE..Const.MAX_RANGE).shuffled().take(Const.NUMBER_COUNT).sorted()
    }
}
