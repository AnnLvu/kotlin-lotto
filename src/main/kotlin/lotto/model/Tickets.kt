package lotto.model

class Tickets(private val ticketList: List<Ticket>) {
    companion object {
        fun generate(amount: Int): Tickets {
            val ticketCount = amount / Const.PRICE
            val list =
                List(ticketCount) {
                    Ticket(Numbers(generateTicketNumbers()))
                }
            return Tickets(list)
        }

        private fun generateTicketNumbers(): List<Int> {
            return (Const.MIN..Const.MAX).shuffled().take(Const.NUMBER_COUNT).sorted()
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
            val match = ticket.numbers.countMatches(winningTicket.winningNumbers)
            val rank =
                if (match == 5 && ticket.numbers.contains(winningTicket.bonusNumber)) {
                    Rank.SECOND
                } else {
                    Rank.valueOf(match, false)
                }
            winStats[rank] = winStats[rank]!! + 1
        }
        return winStats
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
