package lotto

object LottoMachine {
    fun start() {
        val amount = InputView.inputPurchaseAmount()
        val ticketList = generateTickets(amount)
        val winningNumbers = InputView.inputWinningNumbers()
        val bonusNumber = InputView.inputBonusNumber(winningNumbers)
        calculateStats(ticketList, winningNumbers, bonusNumber)
    }

    fun generateTickets(amount: Int) :List<Ticket> {
        val ticketCount = amount/LottoConstants.TICKET_PRICE
        val ticketsList = mutableListOf<Ticket>()
        repeat(ticketCount) {
            val ticket = Ticket(generateTicketNumbers())
            ticketsList.add(ticket)
        }
        OutputView.displayTickets(ticketCount, ticketsList)
        return ticketsList
    }

    private fun generateTicketNumbers(): List<Int> {
        return(1..45).shuffled().take(6).sorted()
    }

    private val winStats = mutableMapOf(
        Rank.FIRST to 0, Rank.SECOND to 0, Rank.THIRD to 0,
        Rank.FOURTH to 0, Rank.FIFTH to 0, Rank.MISS to 0
    )

    private fun calculateStats(tickets: List<Ticket>, winningNumbers: List<Int>, bonusNumber: Int) {
        for(ticket in tickets){
            val match = checkMatch(ticket, winningNumbers)
            if (match == 5 && ticket.getNumbers().contains(bonusNumber)) {
                winStats[Rank.SECOND] = winStats[Rank.SECOND]!! + 1 //Nullable Receiver so requires !!
            } else {
                winStats[Rank.valueOf(match, false)] = winStats[Rank.valueOf(match, false)]!! + 1
            }
        }
    }

    private fun checkMatch(ticket: Ticket, winningNumbers: List<Int>) :Int{
            return (ticket.getNumbers().count {winningNumbers.contains(it)})
    }
}
