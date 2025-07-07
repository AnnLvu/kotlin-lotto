package lotto.model

class Tickets(private val ticketList: List<Ticket>) {
    companion object {
        fun generate(amount: Int, manualTickets: List<Numbers> = emptyList()): Tickets {
            val totalTicketCount = amount / Const.PRICE
            val manualTicketCount = manualTickets.size
            val autoTicketCount = totalTicketCount - manualTicketCount
            val manualTicketList = manualTickets.map { Ticket(it) }
            val autoTicketList = List(autoTicketCount) {
                Ticket(Numbers(generateTicketNumbers()))
            }
            return Tickets(manualTicketList + autoTicketList)
        }

        private fun generateTicketNumbers(): List<Int> {
            return (Const.MIN_RANGE..Const.MAX_RANGE).shuffled().take(Const.NUMBER_COUNT).sorted()
        }
    }

    fun calculateStats(winningTicket: WinningTicket): Map<Rank, Int> {
        val winStats = mutableMapOf(
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

    private fun determineRank(ticket: Ticket, winningTicket: WinningTicket): Rank {
        val matchCount = ticket.numbers.countMatches(winningTicket.winningNumbers)
        return if (matchCount == 5 && ticket.numbers.contains(winningTicket.bonusNumber)) {
            Rank.SECOND
        } else {
            Rank.valueOf(matchCount, false)
        }
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
