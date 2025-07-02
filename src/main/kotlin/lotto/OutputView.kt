package lotto

object OutputView {
    fun displayTickets(ticketCount: Int, ticketsList: List<Ticket>) {
        println("You have purchased $ticketCount tickets.")
        ticketsList.forEach { ticket ->
            println(ticket.numbers.joinToString(prefix = "[", postfix = "]", separator = ","))
        }
    }

    fun displayResults(winStats: Map<Rank, Int>, returnRate: String) {
        println("\nWinning Statistics")
        println("------------------")
        println("3 Matches (${formatNumber(Rank.FIFTH.winningMoney)} KRW) - ${winStats[Rank.FIFTH]} tickets")
        println("4 Matches (${formatNumber(Rank.FOURTH.winningMoney)} KRW) - ${winStats[Rank.FOURTH]} tickets")
        println("5 Matches (${formatNumber(Rank.THIRD.winningMoney)} KRW) - ${winStats[Rank.THIRD]} tickets")
        println("5 Matches + Bonus Ball (${formatNumber(Rank.SECOND.winningMoney)} KRW) - ${winStats[Rank.SECOND]} tickets")
        println("6 Matches (${formatNumber(Rank.FIRST.winningMoney)} KRW) - ${winStats[Rank.FIRST]} tickets")
        println("Total return rate is $returnRate (A rate below 1 means a loss)")
    }

    private fun formatNumber(prizeAmount:Int): String {
        return prizeAmount.toString().reversed().chunked(3).joinToString(",").reversed()
    }
}
