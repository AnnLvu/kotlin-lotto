package lotto

object LottoMachine {
    fun start() {
        val amount = InputView.inputPurchaseAmount()
        val ticketList = generateTickets(amount)
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
        val winningTicket = WinningTicket(winningNumbers, bonusNumber)
        calculateStats(ticketList, winningTicket)
        OutputView.displayResults(winStats, calculateReturnRate(amount))
    }

    fun generateTickets(amount: Int): List<Ticket> {
        val ticketCount = amount / Const.PRICE
        val ticketsList = List(ticketCount) {
            Ticket(Numbers(generateTicketNumbers()))
        }
        OutputView.displayTickets(ticketCount, ticketsList)
        return ticketsList
    }

    private fun generateTicketNumbers(): List<Int> {
        return (Const.MIN..Const.MAX).shuffled().take(Const.NUMBER_COUNT).sorted()
    }

    private val winStats = mutableMapOf(
        Rank.FIRST to 0,
        Rank.SECOND to 0,
        Rank.THIRD to 0,
        Rank.FOURTH to 0,
        Rank.FIFTH to 0,
        Rank.MISS to 0,
    )

    private fun calculateStats(
        tickets: List<Ticket>, winningTicket: WinningTicket
    ) {
        for (ticket in tickets) {
            val match = ticket.numbers.countMatches(winningTicket.winningNumbers)
            if (match == 5 && ticket.numbers.contains(winningTicket.bonusNumber)) {
                winStats[Rank.SECOND] = winStats[Rank.SECOND]!! + 1
            } else {
                winStats[
                    Rank.valueOf(
                        match, false,
                    ),
                ] = winStats[Rank.valueOf(match, false)]!! + 1
            }
        }
    }

    private fun calculateReturnRate(amount: Int): String {
        return (calculateReturnRate(amount, winStats.entries.sumOf { (rank, count) -> rank.prize * count }))
    }

    fun calculateReturnRate(
        amount: Int,
        totalReturn: Int,
    ): String {
        val returnRate = totalReturn.toDouble() / amount
        return String.format("%.2f", returnRate)
    }
}
