package lotto.model

class Tickets(private val ticketList: List<Ticket>) {
    companion object {
        fun generate(
            amount: Int,
            manualTicketsNumbers: List<Numbers> = emptyList(),
        ): Tickets {
            val generator = TicketGenerator(amount, manualTicketsNumbers)
            return Tickets(generator.generateTickets())
        }
    }

    fun calculateStats(winningTicket: WinningTicket): Map<Rank, Int> {
        val winStats =
            mutableMapOf(
                Rank.FIRST to 0,
                Rank.SECOND to 0,
                Rank.THIRD to 0,
                Rank.FOURTH to 0,
                Rank.FIFTH to 0,
                Rank.MISS to 0,
            )

        for (ticket in ticketList) {
            val rank = determineRank(ticket, winningTicket)
            winStats[rank] = winStats[rank]!! + 1
        }
        return winStats
    }

    private fun determineRank(
        ticket: Ticket,
        winningTicket: WinningTicket,
    ): Rank {
        val matchCount = ticket.numbers.countMatches(winningTicket.winningNumbers)
        return if (isSecondRank(matchCount, ticket, winningTicket)) {
            Rank.SECOND
        } else {
            Rank.valueOf(matchCount, false)
        }
    }

    private fun isSecondRank(
        matchCount: Int,
        ticket: Ticket,
        winningTicket: WinningTicket,
    ): Boolean {
        return matchCount == 5 && ticket.numbers.contains(winningTicket.bonusNumber)
    }

    fun calculateReturnRate(
        amount: Int,
        winStats: Map<Rank, Int>,
    ): String {
        val totalReturn = winStats.entries.sumOf { (rank, count) -> rank.prize * count }
        val returnRate = totalReturn.toDouble() / amount
        return String.format("%.2f", returnRate)
    }

    fun getTickets(): List<Ticket> = ticketList
}
